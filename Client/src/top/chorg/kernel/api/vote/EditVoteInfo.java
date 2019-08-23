package top.chorg.kernel.api.vote;

import java.util.Date;

public class EditVoteInfo {

    public int id;
    public String title, compiledContent;
    public boolean isMultiple;
    public String[] choice;
    public Date endDate;

    public EditVoteInfo(int id, String title, String compiledContent, Date endDate,
                        boolean isMultiple, String[] choice) {
        this.id = id;
        this.title = title;
        this.compiledContent = compiledContent;
        this.isMultiple = isMultiple;
        this.choice = choice;
        this.endDate = endDate;
    }
}
