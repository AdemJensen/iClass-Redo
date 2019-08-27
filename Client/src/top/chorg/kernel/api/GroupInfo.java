package top.chorg.kernel.api;

import top.chorg.window.foundation.IImageIcon;

import java.util.Date;

import static top.chorg.kernel.Variable.*;

public class GroupInfo {

    public int id;
    public IImageIcon avatar;
    public String name, introduction;
    public Date date;
    public int[] classmates;

    public GroupInfo(int id, String name, String introduction, Date date, int[] classmates) {
        this.id = id;
        this.name = name;
        this.introduction = introduction;
        this.date = date;
        this.classmates = classmates;
        this.assignAvatar();
    }

    private void assignAvatar() {
        avatar = getAvatar(0, id);
    }

}
