package top.chorg.window.auth;

import top.chorg.window.foundation.notice.IConfirmNoticeFrame;

import java.awt.*;

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

    public void assignData() {  // TODO: 获取用户的信息

    }

    protected void addSubmitListener() {
        buttonPanel.addActionListeners(
                e -> new IConfirmNoticeFrame(
                        "提交修改请求",
                        f -> {
                            // TODO: 提交修改请求
                        }
                ).showWindow(),
                e -> new IConfirmNoticeFrame(
                        "复原已填写的内容",
                        f -> resetInput()
                ).showWindow(),
                e -> {      // 退出前二次确认，确保用户手滑不会造成资料丢失
                    if (isWritten()) {
                        new IConfirmNoticeFrame(
                                "放弃已编辑的内容",
                                f -> this.dispose()
                        ).showWindow();
                    } else {
                        this.dispose();
                    }
                }
        );
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
