package top.chorg.kernel.network;

import top.chorg.kernel.api.auth.GroupInfo;
import top.chorg.kernel.api.auth.LoginRequest;
import top.chorg.kernel.api.auth.RegisterRequest;
import top.chorg.kernel.api.auth.UserInfo;
import top.chorg.kernel.api.file.FileDownloadRequest;
import top.chorg.kernel.foundation.Message;
import top.chorg.window.foundation.IImageIcon;

import java.util.Date;

import static top.chorg.kernel.Variable.*;

public class AuthNet {

    public String login(String username, String password) {
        if (self != null) return "已有用户在线";
        if (!masterCon.connect()) return "无法连接到服务器";
        try {
            self = masterCon.send(
                    new Message(1, new LoginRequest(username, password)), UserInfo.class
            );
            fileNet.downloadAvatar(1, self.id);
            self.assignAvatar();
        } catch (Exception e) {
            return e.getMessage();
        }
        return "";
    }

    public String register(String username, String password, String realName, String stuNum,
                           int sex, String email, String phone, String qq, IImageIcon avatar) {
        try {
            int res = masterCon.post(
                    new Message(
                            3, new RegisterRequest(username, password, realName, stuNum, email, phone, qq, sex)
                    ), int.class
            );
            if (res < 0) return "服务器出现错误";
            if (login(username, password) == null) return "注册成功，但头像上传失败，请稍后在个人主页面重新上传头像";
            avatar.saveImage(temp(getStorageAvatarName(1, res)));
            String uploadRes = fileNet.uploadAvatar(1, res);
            masterCon.disconnect();
            if (uploadRes.length() > 0) {
                return "注册成功，但头像上传失败，请稍后在个人主页面重新上传头像";
            }
        } catch (Exception e) {
            System.err.println(e.getMessage() == null ? "null" : e.getMessage());
            return "无法连接到服务器";
        }
        return "";
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
        // TODO
        return new UserInfo(
                "tester" + id, new Date(), "测试账号" + id, "000000000000",
                "simulation@test.nemo", "15555555555", "1000000000", id, 1
        );
    }

    public GroupInfo getGroupInfo(int id) {
        // TODO
        return new GroupInfo(
                id, "group" + id, "这里是iClass测试班级", new Date(),
                new int[] {1, 2, 3, 4, 5}
        );
    }

}
