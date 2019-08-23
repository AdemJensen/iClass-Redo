package top.chorg.window.auth;

import top.chorg.kernel.api.UserInfo;
import top.chorg.window.foundation.notice.IConfirmNoticeFrame;

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

        this.setLocationCenter(420, 400);
    }

    public void assignData() {
        UserInfo info = authNet.getUserInfo(self.id);
        this.usernamePanel.val(info.username);
        this.realNamePanel.val(info.realName);
        this.avatarIcon.setImage(info.avatar.getImage());
        this.emailPanel.val(info.email);
        this.phonePanel.val(info.phone);
        this.qqPanel.val(info.qq);
        this.stuNumPanel.val(info.stuNum);
        this.sexPanel.val(info.sex);
    }

    public void submitAction() {
        // TODO: 提交修改请求

        this.dispose();
    }

    public void resetInput() {
        assignData();
    }

    public boolean isWritten() {
        return true;    // TODO: 检查是否被修改
    }

    public void dispose() {
        ancestorDispose();
    }
}
