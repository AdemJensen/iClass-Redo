package top.chorg.kernel.api.chat;

import java.util.Date;

public class UnreadChatQueryInfo {

    public int msgType, fromId, amount; // msgType: 0 / 1
    public Date lastDate;
    public String displayContent;   // Process are done when message sent.

    public UnreadChatQueryInfo(int msgType, int fromId, int amount, Date lastDate, String displayContent) {
        this.msgType = msgType;
        this.fromId = fromId;
        this.amount = amount;
        this.lastDate = lastDate;
        this.displayContent = displayContent;
    }
}
