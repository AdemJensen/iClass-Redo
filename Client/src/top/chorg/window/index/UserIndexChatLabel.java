package top.chorg.window.index;

import top.chorg.kernel.api.UserInfo;

import java.util.Date;

public class UserIndexChatLabel extends IndexChatLabel {

    public UserIndexChatLabel(int width, int height, UserInfo userInfo) {
        super(width, height, userInfo.avatar, userInfo.getUserNameDisplay(), new Date(), "TEST MSG", 22);

    }
}
