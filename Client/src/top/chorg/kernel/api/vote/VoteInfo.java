package top.chorg.kernel.api.vote;

import java.util.Date;

import static top.chorg.kernel.Variable.getVoteEditInfoStr;
import static top.chorg.kernel.Variable.getVoteTimeInfoStr;

public class VoteInfo {

    public int id;
    public String title, compiledContent;
    public boolean isMultiple;
    public String[] choice;
    public Date publishDate, editDate, endDate;
    public int uPub, uEdit;

    public VoteInfo(int id, String title, String compiledContent, boolean isMultiple, Date endDate,
                    String[] choice, Date publishDate, Date editDate, int uPub, int uEdit) {
        this.id = id;
        this.title = title;
        this.compiledContent = compiledContent;
        this.isMultiple = isMultiple;
        this.endDate = endDate;
        this.choice = choice;
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
