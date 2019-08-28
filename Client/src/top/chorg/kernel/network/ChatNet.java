package top.chorg.kernel.network;

import top.chorg.kernel.api.chat.ChatMsg;
import top.chorg.kernel.api.chat.UnreadChatQueryInfo;

import java.util.Date;

public class ChatNet {

    public ChatMsg[] getChatHistory(int type, int fromId) {
        return new ChatMsg[] {new ChatMsg(1, 1, type, fromId, new Date(), "[\"icon\",\"34e05d90aaace4799167ce368dc2c650\",\"content\",\"{\\\"color\\\":{\\\"value\\\":-16777216,\\\"falpha\\\":0.0},\\\"family\\\":\\\"Lucida Grande\\\",\\\"size\\\":13,\\\"isItalic\\\":false,\\\"isBold\\\":false,\\\"isUnderline\\\":false,\\\"startOff\\\":1,\\\"len\\\":1,\\\"content\\\":\\\" \\\"}\",\"content\",\"{\\\"color\\\":{\\\"value\\\":-16777216,\\\"falpha\\\":0.0},\\\"family\\\":\\\"Lucida Grande\\\",\\\"size\\\":13,\\\"isItalic\\\":false,\\\"isBold\\\":false,\\\"isUnderline\\\":false,\\\"startOff\\\":2,\\\"len\\\":1,\\\"content\\\":\\\" \\\"}\",\"content\",\"{\\\"color\\\":{\\\"value\\\":-16777216,\\\"falpha\\\":0.0},\\\"family\\\":\\\"Lucida Grande\\\",\\\"size\\\":13,\\\"isItalic\\\":false,\\\"isBold\\\":false,\\\"isUnderline\\\":false,\\\"startOff\\\":3,\\\"len\\\":1,\\\"content\\\":\\\" \\\"}\",\"content\",\"{\\\"color\\\":{\\\"value\\\":-16777216,\\\"falpha\\\":0.0},\\\"family\\\":\\\"Lucida Grande\\\",\\\"size\\\":13,\\\"isItalic\\\":false,\\\"isBold\\\":false,\\\"isUnderline\\\":false,\\\"startOff\\\":4,\\\"len\\\":1,\\\"content\\\":\\\"\\\"}\"]")};
    }

    public UnreadChatQueryInfo[] getUnreadChat() {
        UnreadChatQueryInfo[] list = new UnreadChatQueryInfo[5];
        list[0] = new UnreadChatQueryInfo(0, 1, 222, new Date(), "test1");
        list[1] = new UnreadChatQueryInfo(0, 2, 3, new Date(), "test2");
        list[2] = new UnreadChatQueryInfo(1, 2, 1, new Date(), "test3");
        list[3] = new UnreadChatQueryInfo(1, 3, 0, new Date(), "test4");
        list[4] = new UnreadChatQueryInfo(1, 4, 0, new Date(), "test5");
        return list;
    }

}
