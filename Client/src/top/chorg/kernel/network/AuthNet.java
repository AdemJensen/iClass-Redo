package top.chorg.kernel.network;

import top.chorg.kernel.api.GroupInfo;
import top.chorg.kernel.api.UserInfo;

import java.util.Date;

public class AuthNet {

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
