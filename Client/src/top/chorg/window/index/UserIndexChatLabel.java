package top.chorg.window.index;

import top.chorg.kernel.api.UserInfo;
import top.chorg.window.foundation.IndexChatLabel;

import javax.swing.*;

public class UserIndexChatLabel extends IndexChatLabel {

    JLabel nameLabel, timeLabel, msgLabel;

    public UserIndexChatLabel(int width, int height, UserInfo userInfo) {
        super(width, height);

    }
}
