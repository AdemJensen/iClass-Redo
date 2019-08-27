package top.chorg.kernel.api.chat;

public class FetchChatRequest {
    public int type;   // 0 = to class, 1 = to individual
    public int toId;   // if type = 0, then groupId. if type = 1, then userId.

    public FetchChatRequest(int type, int toId) {
        this.type = type;
        this.toId = toId;
    }
}
