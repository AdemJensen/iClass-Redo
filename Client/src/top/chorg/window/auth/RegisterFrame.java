package top.chorg.window.auth;

import top.chorg.support.FileUtils;
import top.chorg.window.foundation.*;
import top.chorg.window.foundation.button.ILinkedButton;
import top.chorg.window.foundation.form.IFormButtonPanel;
import top.chorg.window.foundation.form.IPasswordFieldPanel;
import top.chorg.window.foundation.form.IRadioBoxPanel;
import top.chorg.window.foundation.form.ITextFieldPanel;
import top.chorg.window.foundation.notice.IConfirmNoticeFrame;
import top.chorg.window.foundation.notice.INoticeFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static top.chorg.kernel.Variable.resource;

public class RegisterFrame extends IFrame {

    IPanel leftMasterPanel, rightMasterPanel, footerPanel;

    ITextFieldPanel usernamePanel, realNamePanel, emailPanel, phonePanel, qqPanel, stuNumPanel;
    IPasswordFieldPanel passwordPanel, confirmPanel;
    IRadioBoxPanel sexPanel;

    JLabel avatarLabel, headerHintLabel, footerHintLabel;
    IImageIcon avatarIcon;
    JButton avatarButton;
    ILinkedButton agreementButton;

    IFormButtonPanel buttonPanel;

    public RegisterFrame() {
        super(
                420, 440,
                "注册用户 - iClass",
                new FlowLayout(FlowLayout.CENTER),
                JFrame.DISPOSE_ON_CLOSE
        );
        this.setLocationCenter(420, 440);
    }

    @Override
    public void addComponents() {
        leftMasterPanel = new IPanel(
                250, 300,
                new EmptyBorder(5, 20, 0, 0),
                new FlowLayout(FlowLayout.CENTER)
        );
        rightMasterPanel = new IPanel(
                140, 300,
                new EmptyBorder(5, 5, 0, 0),
                new FlowLayout(FlowLayout.CENTER)
        );
        footerPanel = new IPanel(
                400, 30,
                new EmptyBorder(0, 10, 0, 0),
                new FlowLayout(FlowLayout.LEFT)
        );

        headerHintLabel = new JLabel("请填写下列内容（带有*的字段为必填字段）");
        headerHintLabel.setPreferredSize(new Dimension(400, 20));
        headerHintLabel.setBorder(new EmptyBorder(5, 10, 0, 10));

        footerHintLabel = new JLabel("当您注册用户时，您已经阅读并同意");
        agreementButton = new ILinkedButton("《软件许可协议》");

        usernamePanel = new ITextFieldPanel(
                210, 30,
                null, 60, 140,
                "*用户名", ""
        );
        passwordPanel = new IPasswordFieldPanel(
                210, 30,
                null, 60, 140,
                "*密码", ""
        );
        confirmPanel = new IPasswordFieldPanel(
                210, 30,
                null, 60, 140,
                "*确认密码", ""
        );
        realNamePanel = new ITextFieldPanel(
                210, 30,
                null, 60, 140,
                "*真实姓名", ""
        );
        stuNumPanel = new ITextFieldPanel(
                210, 30,
                null, 60, 140,
                "*学号", ""
        );
        sexPanel = new IRadioBoxPanel(
                210, null, 60, "*性别", new FlowLayout(FlowLayout.LEFT),
                "汉子", "妹子"
        );
        emailPanel = new ITextFieldPanel(
                210, 30,
                null, 60, 140,
                "电子邮箱", ""
        );
        phonePanel = new ITextFieldPanel(
                210, 30,
                null, 60, 140,
                "手机", ""
        );
        qqPanel = new ITextFieldPanel(
                210, 30,
                null, 60, 140,
                "QQ", ""
        );

        avatarIcon = new IImageIcon(resource("defaultUserIcon.png"));
        avatarIcon.setSize(120, 120);
        avatarIcon.setRadius(120);
        avatarButton = new JButton("选择头像");

        leftMasterPanel.addComp(
                usernamePanel, passwordPanel, confirmPanel,
                realNamePanel, stuNumPanel, sexPanel,
                emailPanel, phonePanel, qqPanel
        );

        footerPanel.addComp(footerHintLabel, agreementButton);

        avatarLabel = new JLabel(avatarIcon);
        rightMasterPanel.addComp(avatarLabel, avatarButton);

        buttonPanel = new IFormButtonPanel(
                400, 30,
                null,
                "提交", "重置", "取消"
        );

        this.addComp(headerHintLabel, leftMasterPanel, rightMasterPanel, footerPanel, buttonPanel);
    }

    public void resetInput() {
        usernamePanel.val("");
        realNamePanel.val("");
        emailPanel.val("");
        phonePanel.val("");
        qqPanel.val("");
        stuNumPanel.val("");
        passwordPanel.val("");
        confirmPanel.val("");
        sexPanel.val(-1);
    }

    public boolean isWritten() {
        return usernamePanel.val().length() > 0 ||
                realNamePanel.val().length() > 0 ||
                emailPanel.val().length() > 0 ||
                phonePanel.val().length() > 0 ||
                qqPanel.val().length() > 0 ||
                stuNumPanel.val().length() > 0 ||
                passwordPanel.val().length() > 0 ||
                confirmPanel.val().length() > 0 ||
                sexPanel.val() >= 0;
    }

    @Override
    public void addListeners() {
        addSubmitListener();

        avatarButton.addActionListener(e -> {
            // TODO 添加头像
        });

        agreementButton.addActionListener(e -> {
            String content = FileUtils.readFileContent(resource("agreement.txt"));
            if (content == null) content = "错误：无法显示软件许可协议";
            INoticeFrame agreementNoticeFrame = new INoticeFrame(
                    "软件许可协议 - iClass",
                    content, true, "确认"
            );
            agreementNoticeFrame.addActionListeners(f -> agreementNoticeFrame.dispose());
            agreementNoticeFrame.showWindow();
        });

    }

    // TODO: pack critical data process methods.
    protected void addSubmitListener() {
        buttonPanel.addActionListeners(
                e -> new IConfirmNoticeFrame(
                        "提交注册请求",
                        f -> submitAction()
                ).showWindow(),
                e -> new IConfirmNoticeFrame(
                        "放弃已填写的内容",
                        f -> resetInput()
                ).showWindow(),
                e -> {      // 退出前二次确认，确保用户手滑不会造成资料丢失
                    if (isWritten()) {
                        new IConfirmNoticeFrame(
                                "放弃已填写的内容",
                                f -> this.dispose()
                        ).showWindow();
                    } else {
                        this.dispose();
                    }
                }
        );
    }

    public void submitAction() {
        // TODO: 提交注册请求

        this.dispose();
    }

    public String[] preValidate() {     // TODO: pre validation
        return new String[0];
    }

    @Override
    public void dispose() {
        new LoginFrame().showWindow();
        super.dispose();
    }

    protected void ancestorDispose() {
        super.dispose();
    }
}
