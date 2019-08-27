package top.chorg.kernel.api.announce;

import java.util.Date;

import static top.chorg.kernel.Variable.getAnnounceEditInfoStr;

public class AnnouncementListInfo {

    public int id, groupId;
    public String title;
    public Date publishDate, editDate;
    public int uPub, uEdit;

    public AnnouncementListInfo(int id, int groupId, String title,
                                Date publishDate, Date editDate, int uPub, int uEdit) {
        this.id = id;
        this.groupId = groupId;
        this.title = title;
        this.publishDate = publishDate;
        this.editDate = editDate;
        this.uPub = uPub;
        this.uEdit = uEdit;
    }

    public String getEditInfoStr() {
        return getAnnounceEditInfoStr(publishDate, editDate, uPub, uEdit);
    }

}
