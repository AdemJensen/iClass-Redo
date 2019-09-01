package top.chorg.kernel.api.auth;

import java.util.Date;

public class RegisterRequest {

    public String username, password, realName, stuNum, email, phone, qq;
    public int sex;
    public Date regTime;

    public RegisterRequest(String username, String password, String realName, String stuNum,
                           String email, String phone, String qq, int sex) {
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.stuNum = stuNum;
        this.email = email;
        this.phone = phone;
        this.qq = qq;
        this.sex = sex;
    }
}
