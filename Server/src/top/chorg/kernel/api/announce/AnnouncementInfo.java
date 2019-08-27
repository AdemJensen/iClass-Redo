package top.chorg.kernel.api.announce;

import java.util.Date;

public class AnnouncementInfo {

    public int id;
    public String title, compiledContent;
    public Date publishDate, editDate;
    public int uPub, uEdit;

    public AnnouncementInfo(int id, String title, String compiledContent,
                            Date publishDate, Date editDate, int uPub, int uEdit) {
        this.id = id;
        this.title = title;
        this.compiledContent = compiledContent;
        this.publishDate = publishDate;
        this.editDate = editDate;
        this.uPub = uPub;
        this.uEdit = uEdit;
    }

}
