package top.chorg.kernel.api.vote;

import java.util.Date;

public class AddVoteInfo {

    public String title, compiledContent;
    public boolean isMultiple;
    public String[] choice;
    public Date endDate;

    public AddVoteInfo(String title, String compiledContent, boolean isMultiple, String[] choice, Date endDate) {
        this.title = title;
        this.compiledContent = compiledContent;
        this.isMultiple = isMultiple;
        this.choice = choice;
        this.endDate = endDate;
    }
}
