package top.chorg.kernel.api;

import top.chorg.window.foundation.IImageIcon;

import java.util.Date;

import static top.chorg.kernel.Variable.*;

public class UserInfo {

    // sex: 0 = boy, 1 = girl
    public String username, realName, stuNum, email, phone, qq;
    public int id, sex;
    public Date regTime;
    public IImageIcon avatar;

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
        this.assignAvatar();
    }

    private void assignAvatar() {
        avatar = getAvatar(1, id);
    }

}
