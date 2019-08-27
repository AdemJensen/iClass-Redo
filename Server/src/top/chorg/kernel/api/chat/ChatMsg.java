package top.chorg.kernel.api.chat;

import java.util.Date;

public class ChatMsg {
    public int id;
    public int fromId;  // From userId.
    public int type;   // 0 = to class, 1 = to individual
    public int toId;   // if type = 0, then groupId. if type = 1, then userId.
    public Date time;
    public String content;

    public ChatMsg(int type, int toId, String content) {
        this.type = type;
        this.toId = toId;
        this.content = content;
    }

    public ChatMsg(int id, int fromId, int type, int toId, Date time, String content) {
        this.id = id;
        this.fromId = fromId;
        this.type = type;
        this.toId = toId;
        this.time = time;
        this.content = content;
    }

}
