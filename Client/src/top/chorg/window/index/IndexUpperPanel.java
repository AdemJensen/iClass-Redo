package top.chorg.window.index;

import top.chorg.kernel.api.UserInfo;
import top.chorg.window.foundation.IClickableAdapter;
import top.chorg.window.foundation.button.IImageButton;
import top.chorg.window.foundation.IImageIcon;
import top.chorg.window.foundation.IPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static top.chorg.kernel.Variable.resource;

public class IndexUpperPanel extends IPanel {

    IPanel userInfoPanel, upperLocatorPanel1;
    JLabel avatarLabel, userNameLabel;
    IImageButton settingsButton;
    IImageIcon avatarIcon;

    public IndexUpperPanel(UserInfo selfInfo) {
        super(1100, 60,
                null,
                new FlowLayout(FlowLayout.CENTER)
        );
        this.setBackground(new Color(230, 230, 230));

        this.userInfoPanel = new IPanel(
                300, 50,
                null,
                new FlowLayout(FlowLayout.LEFT)
        );
        this.userInfoPanel.setBackground(new Color(230, 230, 230));
        this.userInfoPanel.addMouseListener(new IClickableAdapter(this.userInfoPanel));

        this.upperLocatorPanel1 = new IPanel(730, 45);
        this.upperLocatorPanel1.setBackground(new Color(230, 230, 230));
        this.refreshSelfAvatar(selfInfo);

        userNameLabel = new JLabel(selfInfo.getUserNameDisplay());
        userNameLabel.setPreferredSize(new Dimension(245, 40));
        userNameLabel.setBorder(new EmptyBorder(0, 5, 0, 0));

        settingsButton = new IImageButton(50, 50, resource("settingsIcon.png"));

        userInfoPanel.addComp(avatarLabel, userNameLabel);
        this.addComp(userInfoPanel, upperLocatorPanel1, settingsButton);

    }

    public void refreshSelfAvatar(UserInfo selfInfo) {
        this.avatarIcon = selfInfo.avatar;
        this.avatarIcon.setSize(40, 40);
        this.avatarIcon.setRadius(40);
        this.avatarLabel = new JLabel(avatarIcon);
        this.avatarLabel.setPreferredSize(new Dimension(40, 40));
    }
}
