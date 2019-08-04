package top.chorg.window.foundation;

import top.chorg.kernel.foundation.DateTime;

import javax.swing.*;
import java.awt.*;

/**
 * 首页的联系人标签基类
 */
public abstract class IndexChatLabel extends IClickablePanel {

    public int width, height;
    public IPanel infoPanel;
    public JLabel avatarLabel, nameLabel, timeLabel, msgLabel, numberLabel;
    public IImageIcon avatarIcon;

    public IndexChatLabel(int width, int height) {
        super(width, height, null, new FlowLayout(FlowLayout.CENTER));
    }

    public IndexChatLabel(int width, int height, IImageIcon avatar, IPanel infoPanel,
                          String talker, DateTime msgTime, String lastMsg, int msgAmount) {
        this(width, height);
        init(width, height, avatar, talker, msgTime, lastMsg, msgAmount);
    }

    public void init(int width, int height, IImageIcon avatar,
                     String talkerName, DateTime msgTime, String lastMsg, int msgAmount) {
        this.width = width;
        this.height = height;
        this.assignAvatar(avatar);
        this.nameLabel = new JLabel(talkerName);

        this.addComp(avatarLabel, infoPanel);
    }

    public void assignAvatar(IImageIcon avatar) {
        this.avatarIcon = avatar;
        this.avatarIcon.setSize(height - 10, height - 10);
        this.avatarIcon.setRadius(height - 10);
        this.avatarLabel = new JLabel(avatarIcon);
        this.avatarLabel.setPreferredSize(new Dimension(height - 5, height - 5));
    }



}
