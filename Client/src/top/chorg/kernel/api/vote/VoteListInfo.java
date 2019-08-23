package top.chorg.kernel.api.vote;

import java.util.Date;

import static top.chorg.kernel.Variable.getVoteEditInfoStr;
import static top.chorg.kernel.Variable.getVoteTimeInfoStr;

public class VoteListInfo {

    public int id, classId;
    public String title;
    public Date publishDate, editDate, endDate;
    public int uPub, uEdit;

    public VoteListInfo(int id, int classId, String title, Date endDate,
                        Date publishDate, Date editDate, int uPub, int uEdit) {
        this.id = id;
        this.classId = classId;
        this.title = title;
        this.endDate = endDate;
        this.publishDate = publishDate;
        this.editDate = editDate;
        this.uPub = uPub;
        this.uEdit = uEdit;
    }

    public String getTimeInfoStr() {
        return getVoteTimeInfoStr(endDate);
    }

    public String getEditInfoStr() {
        return getVoteEditInfoStr(publishDate, editDate, uPub, uEdit);
    }

}
