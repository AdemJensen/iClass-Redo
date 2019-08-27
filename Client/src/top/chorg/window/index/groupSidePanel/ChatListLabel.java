package top.chorg.window.index.groupSidePanel;

import top.chorg.kernel.foundation.enumClass.UserLevel;
import top.chorg.window.foundation.IClickableAdapter;
import top.chorg.window.foundation.IImageIcon;
import top.chorg.window.foundation.IPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static top.chorg.kernel.Variable.masterFrame;
import static top.chorg.kernel.Variable.resource;

public class ChatListLabel extends IPanel {

    public int targetId;

    public int width, height;
    public JLabel avatarLabel, nameLabel, managerSignLabel;
    public IImageIcon avatarIcon, managerSignIcon;

    public ChatListLabel(int width, int height, int targetId,
                         IImageIcon avatar, String talker, int userLevel, boolean isOnline) {
        super(width, height, null, new FlowLayout(FlowLayout.LEFT));
        init(width, height, targetId, avatar, talker, userLevel, isOnline);
    }

    public void init(int width, int height, int targetId,
                     IImageIcon avatar, String talker, int userLevel, boolean isOnline) {
        this.targetId = targetId;
        this.width = width;
        this.height = height;
        this.assignAvatar(avatar, isOnline);
        this.nameLabel = new JLabel(talker);
        this.setBackground(new Color(0xf6f6f6));
        this.addMouseListener(new IClickableAdapter(this));
        nameLabel.setPreferredSize(new Dimension(width - height * 2, height - 10));
        if (!isOnline) {
            this.nameLabel.setForeground(new Color(0x919191));
        } else {
            this.nameLabel.setForeground(new Color(0x000000));
        }
        this.addComp(avatarLabel, nameLabel);
        this.assignManagerSign(userLevel);
        if (managerSignIcon != null) this.add(managerSignLabel);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                masterFrame.turnToChat(1, targetId);
            }
        });
    }

    public void assignAvatar(IImageIcon avatar, boolean isOnline) {
        this.avatarIcon = avatar;
        if (!isOnline) avatar.gray();
        // else avatar.revGrey();
        this.avatarIcon.setSize(height - 10, height - 10);
        this.avatarIcon.setRadius(height - 10);
        this.avatarLabel = new JLabel(avatarIcon);
        this.avatarLabel.setPreferredSize(new Dimension(height - 10, height - 10));
    }

    public void assignManagerSign(int level) {
        this.managerSignIcon = null;
        switch (level) {
            case UserLevel.MANAGER:
                this.managerSignIcon = new IImageIcon(resource("manager.png"));
                break;
            case UserLevel.SUPER_MANAGER:
                this.managerSignIcon = new IImageIcon(resource("superManager.png"));
                break;
        }
        if (managerSignIcon != null) {
            this.managerSignIcon.setSize(height - 14, height - 14);
            this.managerSignLabel = new JLabel(managerSignIcon);
            this.avatarLabel.setBorder(new EmptyBorder(2, 2, 2, 2));
            this.managerSignLabel.setPreferredSize(new Dimension(height - 10, height - 10));
        }
    }
}
