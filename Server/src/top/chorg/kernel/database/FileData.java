package top.chorg.kernel.database;

import top.chorg.kernel.api.file.FileListQueryInfo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static top.chorg.kernel.Variable.database;

public class FileData {

    /**
     * 获取文件列表
     * @param type 要获取的是用户之间的列表还是班级列表，
     *             0 = 班级列表
     *             1 = 用户列表
     * @param targetId 文件发送目标
     *                 若type = 0，则targetId为班级编号
     *                 若type = 1，则targetId为目标用户编号
     * @return 获取到的用户列表
     */
    public FileListQueryInfo getFileList(int type, int targetId, int page) {
        return null;
    }

    /**
     * 用于向服务器中注册文件信息，不要被Dispatcher调用
     *
     * @param owner 文件拥有者
     *              若type = 0，则targetId为班级编号
     *              若type = 1，则targetId为目标用户编号
     * @param targetId 文件发送的对象id
     *                 0 = 班级列表
     *                 1 = 用户列表
     * @param storageType 文件发送的对象类型
     * @param name 上传的文件原本文件名
     * @param hash 文件的hash值
     * @return 文件在数据库中的id
     */
    public int registerFile(int owner, int targetId, int storageType, String name, String hash) {
        try {
            PreparedStatement state = database.prepareStatement(
                    "INSERT INTO iClass.files (owner, targetId, storageType, name, hash, size, uploadDate)" +
                            " VALUES (?, ?, ?, ?, ?, -1, current_timestamp())",
                    Statement.RETURN_GENERATED_KEYS
            );
            state.setInt(1, owner);
            state.setInt(2, targetId);
            state.setInt(3, storageType);
            state.setString(4, name);
            state.setString(5, hash);
            if (state.executeUpdate() <= 0) throw new SQLException("Invalid action");
            var rs = state.getGeneratedKeys(); //获取结果
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("Invalid fileID");
            }
        } catch (SQLException e) {
            System.err.printf("Error while creating file record (%s)\n", e.getMessage());
            return -1;
        }
    }

    public void completeRegFile(int fileId, long size) {
        try {
            PreparedStatement state = database.prepareStatement(
                    "UPDATE iClass.files SET size=? WHERE id=?"
            );
            state.setLong(1, size);
            state.setInt(2, fileId);
            state.executeUpdate();
        } catch (SQLException e) {
            System.err.printf("Error while completing file record (%s)\n", e.getMessage());
        }
    }

    public String getFileHash(int id) {
        try {
            PreparedStatement state = database.prepareStatement(
                    "SELECT hash FROM iClass.files WHERE id=?"
            );
            state.setInt(1, id);
            var res = state.executeQuery();
            if (!res.next()) return null;
            return res.getString("hash");
        } catch (SQLException e) {
            System.err.printf("Error while completing file record (%s)\n", e.getMessage());
            return null;
        }
    }

}
