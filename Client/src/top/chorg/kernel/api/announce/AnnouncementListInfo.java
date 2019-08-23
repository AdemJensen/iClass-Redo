package top.chorg.kernel.api.announce;

import java.util.Date;

import static top.chorg.kernel.Variable.getAnnounceInfoStr;

public class AnnouncementListInfo {

    public int id, classId;
    public String title;
    public Date publishDate, editDate;
    public int uPub, uEdit;

    public AnnouncementListInfo(int id, int classId, String title,
                                Date publishDate, Date editDate, int uPub, int uEdit) {
        this.id = id;
        this.classId = classId;
        this.title = title;
        this.publishDate = publishDate;
        this.editDate = editDate;
        this.uPub = uPub;
        this.uEdit = uEdit;
    }

    public String getInfoStr() {
        return getAnnounceInfoStr(publishDate, editDate, uPub, uEdit);
    }

}
