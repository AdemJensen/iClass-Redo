package top.chorg.window.auth;

import java.awt.*;

import static top.chorg.kernel.Variable.authNet;
import static top.chorg.kernel.Variable.self;

public class EditProfileFrame extends RegisterFrame {

    public EditProfileFrame() {
        super();
        this.setTitle("编辑用户信息 - iClass");
        this.setSize(420, 400);
        this.headerHintLabel.setText("请按需修改下列内容（带有*的字段不能留空）");
        this.remove(footerPanel);
        usernamePanel.textField.setEditable(false);
        usernamePanel.textField.setBackground(new Color(0, 0, 0, 0));

        this.assignData();

        this.setLocationCenter(420, 400);
    }

    public void assignData() {
        self = authNet.getUserInfo(self.id);
        this.usernamePanel.val(self.username);
        this.realNamePanel.val(self.realName);
        this.avatarIcon.setImage(self.avatar.getImage());
        this.avatarIcon.setSize(120, 120);
        this.avatarIcon.setRadius(120);
        this.emailPanel.val(self.email);
        this.phonePanel.val(self.phone);
        this.qqPanel.val(self.qq);
        this.stuNumPanel.val(self.stuNum);
        this.sexPanel.val(self.sex);
        this.repaint();
    }

    public void submitAction() {
        // TODO: 提交修改请求

        this.dispose();
    }

    public void resetInput() {
        assignData();
    }

    public boolean isWritten() {
        return !realNamePanel.val().equals(self.realName) ||
                !emailPanel.val().equals(self.email) ||
                !phonePanel.val().equals(self.phone) ||
                !qqPanel.val().equals(self.qq) ||
                !stuNumPanel.val().equals(self.stuNum) ||
                passwordPanel.val().length() > 0 ||
                confirmPanel.val().length() > 0 ||
                sexPanel.val() != self.sex;
    }

    public void dispose() {
        ancestorDispose();
    }
}
