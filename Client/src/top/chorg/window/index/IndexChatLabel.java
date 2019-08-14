package top.chorg.window.index;

import top.chorg.window.foundation.IClickablePanel;
import top.chorg.window.foundation.IImageIcon;
import top.chorg.window.foundation.IPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

import static top.chorg.kernel.Variable.getTimeDurText;

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

    public IndexChatLabel(int width, int height, IImageIcon avatar,
                          String talker, Date msgTime, String lastMsg, int msgAmount) {
        this(width, height);
        init(width, height, avatar, talker, msgTime, lastMsg, msgAmount);
    }

    public void init(int width, int height, IImageIcon avatar,
                     String talkerName, Date msgTime, String lastMsg, int msgAmount) {
        this.width = width;
        this.height = height;
        this.assignAvatar(avatar);
        this.assignInfoPanel(talkerName, msgTime, lastMsg, msgAmount);
        this.addComp(avatarLabel, infoPanel);
    }

    public void assignAvatar(IImageIcon avatar) {
        this.avatarIcon = avatar;
        this.avatarIcon.setSize(height - 10, height - 10);
        this.avatarIcon.setRadius(height - 10);
        this.avatarLabel = new JLabel(avatarIcon);
        this.avatarLabel.setPreferredSize(new Dimension(height - 5, height - 5));
    }

    public void assignInfoPanel(String talkerName, Date msgTime, String lastMsg, int msgAmount) {
        this.infoPanel = new IPanel(
                width - height - 5, height - 10,
                null, new FlowLayout(FlowLayout.LEFT)
        );
        infoPanel.setOpaque(false);
        this.nameLabel = new JLabel(talkerName);
        this.nameLabel.setPreferredSize(new Dimension(
                (int) ((width - height) * 0.8 - 5), height / 2 - 10)
        );
        this.timeLabel = new JLabel(getTimeDurText(msgTime));
        this.timeLabel.setPreferredSize(new Dimension(
                (int) ((width - height) * 0.2 - 10), height / 2 - 10)
        );
        this.timeLabel.setForeground(new Color(0x919191));
        this.msgLabel = new JLabel(lastMsg);
        this.msgLabel.setPreferredSize(new Dimension(
                (int) ((width - height) * 0.85 - 5), height / 2 - 10)
        );
        this.msgLabel.setForeground(new Color(0x919191));
        this.numberLabel = new JLabel(msgAmount > 99 ? "99+" : String.valueOf(msgAmount), JLabel.CENTER);
        this.numberLabel.setPreferredSize(new Dimension(
                (int) ((width - height) * 0.15 - 10), height / 2 - 10)
        );
        this.numberLabel.setOpaque(true);
        this.numberLabel.setBackground(new Color(0xE82A21));
        this.numberLabel.setForeground(new Color(0xFFFFFF));
        this.infoPanel.addComp(nameLabel, timeLabel, msgLabel);
        if (msgAmount > 0) this.infoPanel.addComp(numberLabel);
    }

}
