package top.chorg.kernel.api;

import top.chorg.window.foundation.IImageIcon;

import static top.chorg.kernel.Variable.resource;
import static top.chorg.kernel.Variable.temp;

public class UserInfo {

    public String username, realName, stuNum, email, phone;
    public int id, sex;
    public IImageIcon avatar;

    public UserInfo(String username, String realName, String stuNum, String email, String phone, int id, int sex) {
        this.username = username;
        this.realName = realName;
        this.stuNum = stuNum;
        this.email = email;
        this.phone = phone;
        this.id = id;
        this.sex = sex;
        assignAvatar();
    }

    private void assignAvatar() {
        avatar = new IImageIcon(temp(String.format("avatar@%d.png", id)));
        if (!avatar.isValid()) {
            avatar = new IImageIcon(resource("defaultUserIcon.png"));
        }
    }

    public String getUserNameDisplay() {
        return String.format("%s [%s]", this.username, this.realName);
    }

}
