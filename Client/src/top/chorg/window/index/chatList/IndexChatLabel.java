package top.chorg.window.index.chatList;

import top.chorg.window.foundation.IClickableAdapter;
import top.chorg.window.foundation.IImageIcon;
import top.chorg.window.foundation.IPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Date;

import static top.chorg.support.TimeUtils.getTimeDurText;

/**
 * 首页的联系人标签基类
 */
public abstract class IndexChatLabel extends IPanel {

    public int width, height, labelHeight;
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
        this.labelHeight = (height - 15) / 2;
        this.setBackground(new Color(0xf6f6f6));
        this.addMouseListener(new IClickableAdapter(this));
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
        layout.setVgap(0);
        layout.setHgap(0);
        this.setLayout(layout);
        this.assignAvatar(avatar);
        this.assignInfoPanel(talkerName, msgTime, lastMsg, msgAmount);
        this.addComp(avatarLabel, infoPanel);
    }

    public void assignAvatar(IImageIcon avatar) {
        this.avatarIcon = avatar;
        this.avatarIcon.setSize(height - 10, height - 10);
        this.avatarIcon.setRadius(height - 10);
        this.avatarLabel = new JLabel(avatarIcon);
        this.avatarLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.avatarLabel.setPreferredSize(new Dimension(height, height));
    }

    public void assignInfoPanel(String talkerName, Date msgTime, String lastMsg, int msgAmount) {
        this.infoPanel = new IPanel(
                width - height, height,
                null, new FlowLayout(FlowLayout.LEFT)
        );
        infoPanel.setOpaque(false);

        this.nameLabel = new JLabel(talkerName);
        this.nameLabel.setPreferredSize(new Dimension(width - height - 15 - 46, labelHeight));

        this.timeLabel = new JLabel(getTimeDurText(msgTime), JLabel.CENTER);
        this.timeLabel.setPreferredSize(new Dimension(46, labelHeight));
        this.timeLabel.setForeground(new Color(0x919191));

        this.msgLabel = new JLabel(lastMsg);
        this.msgLabel.setPreferredSize(new Dimension(width - height - 21 - 30, labelHeight));
        this.msgLabel.setForeground(new Color(0x919191));

        this.numberLabel = new JLabel(msgAmount > 99 ? "99+" : String.valueOf(msgAmount), JLabel.CENTER);
        this.numberLabel.setPreferredSize(new Dimension(30, labelHeight));
        this.numberLabel.setOpaque(true);
        this.numberLabel.setBackground(new Color(0xE82A21));
        this.numberLabel.setForeground(new Color(0xFFFFFF));

        this.infoPanel.addComp(nameLabel, timeLabel, msgLabel);
        if (msgAmount > 0) this.infoPanel.addComp(numberLabel);
    }

}
