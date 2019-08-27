package top.chorg.kernel.api.auth;

import java.util.Date;

public class GroupInfo {

    public int id;
    public String name, introduction;
    public Date date;
    public int[] classmates;

    public GroupInfo(int id, String name, String introduction, Date date, int[] classmates) {
        this.id = id;
        this.name = name;
        this.introduction = introduction;
        this.date = date;
        this.classmates = classmates;
    }

}
