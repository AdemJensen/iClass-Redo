package top.chorg.window.auth;

import top.chorg.kernel.api.auth.UserInfo;
import top.chorg.support.TimeUtils;
import top.chorg.window.foundation.IFrame;
import top.chorg.window.foundation.IImageIcon;
import top.chorg.window.foundation.IPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UserProfileFrame extends IFrame {

    IImageIcon avatar;
    JLabel avatarLabel, usernameLabel, realNameLabel,
            emailLabel, phoneLabel, qqLabel, stuNumLabel, sexLabel, regTimeLabel;
    IPanel leftPanel, rightPanel, lowerPanel;
    JButton editButton;

    public UserProfileFrame(UserInfo user) {
        this(user, null);
    }

    public UserProfileFrame(UserInfo user, ActionListener editButtonAction) {
        super(380, editButtonAction == null ? 230 : 260,
                user.realName + "的个人资料",
                new FlowLayout(FlowLayout.CENTER),
                JFrame.DISPOSE_ON_CLOSE
        );

        leftPanel = new IPanel(170, 190, null, new FlowLayout(FlowLayout.LEFT));
        rightPanel = new IPanel(190, 190, null);
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
        layout.setVgap(8);
        rightPanel.setLayout(layout);

        avatar = new IImageIcon(user.avatar);
        avatar.setSize(150, 150);
        avatar.setRadius(150);
        avatarLabel = new JLabel(avatar);

        usernameLabel = new JLabel("用户名：" + user.username);
        usernameLabel.setPreferredSize(new Dimension(180, 15));

        realNameLabel = new JLabel("真名：" + user.realName);
        realNameLabel.setPreferredSize(new Dimension(180, 15));

        sexLabel = new JLabel("是个" + (user.sex == 0 ? "帅气的小哥哥" : "漂亮的小姐姐"));
        sexLabel.setPreferredSize(new Dimension(180, 15));

        stuNumLabel = new JLabel("学号：" + user.stuNum);
        stuNumLabel.setPreferredSize(new Dimension(180, 15));

        emailLabel = new JLabel("邮箱：" + user.email);
        emailLabel.setPreferredSize(new Dimension(180, 15));

        phoneLabel = new JLabel("手机号：" + user.phone);
        phoneLabel.setPreferredSize(new Dimension(180, 15));

        qqLabel = new JLabel("QQ号：" + user.qq);
        qqLabel.setPreferredSize(new Dimension(180, 15));

        regTimeLabel = new JLabel("注册时间：" + TimeUtils.dateToStr(user.regTime));
        regTimeLabel.setPreferredSize(new Dimension(180, 15));

        rightPanel.addComp(usernameLabel, realNameLabel, sexLabel, stuNumLabel,
                emailLabel, phoneLabel, qqLabel, regTimeLabel);
        leftPanel.addComp(avatarLabel);

        this.addComp(leftPanel, rightPanel);

        if (editButtonAction != null) {
            this.editButton = new JButton("编辑资料");
            editButton.addActionListener(editButtonAction);
            this.lowerPanel = new IPanel(340, 50, null, new FlowLayout(FlowLayout.CENTER));
            this.lowerPanel.add(editButton);
            this.add(lowerPanel);
        }

        this.setLocationCenter(380, editButtonAction == null ? 230 : 260);

    }
}
