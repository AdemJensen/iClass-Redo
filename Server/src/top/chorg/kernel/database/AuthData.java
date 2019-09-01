package top.chorg.kernel.database;

import top.chorg.kernel.api.auth.GroupInfo;
import top.chorg.kernel.api.auth.RegisterRequest;
import top.chorg.kernel.api.auth.UserInfo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import static top.chorg.kernel.Variable.database;

public class AuthData {

    public UserInfo login(String username, String password) {
        try {
            PreparedStatement state = database.prepareStatement(
                    "SELECT id, username, realName, sex, stuNum, email, phone, qq, regTime" +
                            " FROM iClass.users WHERE username=? AND password=?"
            );
            state.setString(1, username);
            state.setString(2, password);
            return assignUserInfo(state);
        } catch (SQLException e) {
            System.err.printf("Error while validating user (%s)\n", e.getMessage());
            return null;
        }
    }

    private static UserInfo assignUserInfo(PreparedStatement state) throws SQLException {
        var res = state.executeQuery();
        if (!res.next()) return null;
        int userId = res.getInt("id");
        return new UserInfo(
                res.getString("username"),
                new Date(res.getTimestamp("regTime").getTime()),
                res.getString("realName"),
                res.getString("stuNum"),
                res.getString("email"),
                res.getString("phone"),
                res.getString("qq"),
                res.getInt("id"),
                res.getInt("sex")
        );
    }

    public boolean checkUsernameAvail(String username) {
        try {
            PreparedStatement state = database.prepareStatement(
                    "SELECT username FROM iClass.users WHERE username=?"
            );
            state.setString(1, username);
            var res = state.executeQuery();
            if (!res.next()) return true;
        } catch (SQLException e) {
            System.err.printf("Error while checking username (%s)\n", e.getMessage());
            return false;
        }
        return false;
    }

    public int register(RegisterRequest request) {
        try {
            PreparedStatement state = database.prepareStatement(
                    "INSERT INTO iClass.users (username, password, realName, sex, stuNum, email, phone, " +
                            "qq, regTime) VALUES (?, ?, ?, ?, ?, ?, ?, ?, current_timestamp())",
                    Statement.RETURN_GENERATED_KEYS
            );
            state.setString(1, request.username);
            state.setString(2, request.password);
            state.setString(3, request.realName);
            state.setInt(4, request.sex);
            state.setString(5, request.stuNum);
            state.setString(6, request.email);
            state.setString(7, request.phone);
            state.setString(8, request.qq);
            if (state.executeUpdate() <= 0) throw new SQLException("Invalid action");
            var rs = state.getGeneratedKeys(); //获取结果
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("Invalid userID");
            }
        } catch (SQLException e) {
            System.err.printf("Error while creating user (%s)\n", e.getMessage());
            return -1;
        }
    }

    public String[] getRealName(int...id) {
        // TODO
        String[] str = new String[id.length];
        for (int i = 0; i < id.length; i++) {
            str[i] = "UserNof" + id[i];
        }
        return str;
    }

    public String[] getGroupName(int...id) {
        // TODO
        String[] str = new String[id.length];
        for (int i = 0; i < id.length; i++) {
            str[i] = "GroupNof" + id[i];
        }
        return str;
    }

    public boolean[] isUserOnline(int...id) {
        // TODO
        boolean[] str = new boolean[id.length];
        for (int i = 0; i < id.length; i++) {
            str[i] = id[i] < 6;
        }
        return str;
    }

    public int[] getLevelInGroup(int groupId, int...id) {
        // TODO
        int[] lev = new int[id.length];
        for (int i = 0; i < id.length; i++) {
            if (id[i] == 1) lev[i] = 3;
            else if (id[i] == 2) lev[i] = 2;
            else if (id[i] < 5) lev[i] = 1;
            else lev[i] = 0;
        }
        return lev;
    }

    public UserInfo getUserInfo(int id) {
        try {
            PreparedStatement state = database.prepareStatement(
                    "SELECT id, username, realName, sex, stuNum, email, phone, qq, regTime" +
                            " FROM iClass.users WHERE id=?"
            );
            state.setInt(1, id);
            return assignUserInfo(state);
        } catch (SQLException e) {
            System.err.printf("Error while getting user info (%s)\n", e.getMessage());
            return null;
        }
    }

    public GroupInfo getGroupInfo(int id) {
        // TODO
        return new GroupInfo(
                id, "group" + id, "这里是iClass测试班级", new Date(),
                new int[] {1, 2, 3, 4, 5}
        );
    }

}
