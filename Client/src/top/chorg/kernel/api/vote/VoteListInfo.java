package top.chorg.kernel.api.vote;

import java.util.Date;

import static top.chorg.kernel.Variable.getVoteEditInfoStr;
import static top.chorg.kernel.Variable.getVoteTimeInfoStr;

public class VoteListInfo {

    public int id, groupId;
    public String title;
    public Date publishDate, editDate, endDate;
    public int uPub, uEdit;

    public VoteListInfo(int id, int groupId, String title, Date endDate,
                        Date publishDate, Date editDate, int uPub, int uEdit) {
        this.id = id;
        this.groupId = groupId;
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
