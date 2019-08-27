package top.chorg.kernel.api.auth;

import java.util.Date;

public class UserInfo {

    // sex: 0 = boy, 1 = girl
    public String username, realName, stuNum, email, phone, qq;
    public int id, sex;
    public Date regTime;

    public UserInfo(String username, Date regTime, String realName, String stuNum,
                    String email, String phone, String qq, int id, int sex) {
        this.username = username;
        this.regTime = regTime;
        this.realName = realName;
        this.stuNum = stuNum;
        this.email = email;
        this.phone = phone;
        this.qq = qq;
        this.id = id;
        this.sex = sex;
    }

}
