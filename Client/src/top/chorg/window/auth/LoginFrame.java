package top.chorg.window.auth;

import com.google.gson.JsonParseException;
import top.chorg.kernel.api.auth.UserInfo;
import top.chorg.support.MD5;
import top.chorg.support.validator.auth.PasswordValidator;
import top.chorg.support.validator.auth.UsernameValidator;
import top.chorg.window.foundation.form.IFormButtonPanel;
import top.chorg.window.foundation.IFrame;
import top.chorg.window.foundation.IPanel;
import top.chorg.window.foundation.form.IPasswordFieldPanel;
import top.chorg.window.foundation.form.ITextFieldPanel;
import top.chorg.window.foundation.notice.IInformationFrame;
import top.chorg.window.index.MasterFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static top.chorg.kernel.Variable.*;

public class LoginFrame extends IFrame {

    IPanel usernamePanelBase, passwordPanelBase;
    IFormButtonPanel buttonPanel;
    ITextFieldPanel usernamePanel;
    IPasswordFieldPanel passwordPanel;
    JButton registerButton, forgotPasswordButton;

    public LoginFrame() {
        super(
                340, 180,
                "登录 - iClass",
                JFrame.EXIT_ON_CLOSE
        );
        this.setLocationCenter(340, 180);
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(0);
        layout.setHgap(0);
        this.setLayout(layout);
    }

    @Override
    public void addComponents() {
        usernamePanelBase = new IPanel(
                340, 65,
                new EmptyBorder(15, 20, 0, 20),
                new FlowLayout(FlowLayout.CENTER)
        );
        usernamePanel = new ITextFieldPanel(
                190, 40,
                null,
                40, 130,
                "用户名",
                ""
        );
        registerButton = new JButton("注册用户");
        usernamePanelBase.addComp(usernamePanel, registerButton);


        passwordPanelBase = new IPanel(
                340, 55,
                new EmptyBorder(0, 20, 5, 20),
                new FlowLayout(FlowLayout.CENTER)
        );
        passwordPanel = new IPasswordFieldPanel(
                190, 40,
                null,
                40, 130,
                "密码",
                ""
        );
        forgotPasswordButton = new JButton("忘记密码");
        passwordPanelBase.addComp(passwordPanel, forgotPasswordButton);

        buttonPanel = new IFormButtonPanel(
                340, 35,
                null,
                "登录", "重置"
        );
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(0);
        layout.setHgap(10);
        buttonPanel.setLayout(layout);

        this.addComp(usernamePanelBase, passwordPanelBase, buttonPanel);

    }

    @Override
    public void addListeners() {

        buttonPanel.addActionListeners(
                e -> {
                    buttonPanel.buttonSet[0].setText("登录中...");
                    String[] errors = new UsernameValidator(usernamePanel.val()).validate("用户名");
                    if (errors.length > 0) {
                        new IInformationFrame("登录失败", errors[0], false).showWindow();
                        buttonPanel.buttonSet[0].setText("登录");
                        return;
                    }
                    errors = new PasswordValidator(passwordPanel.val()).validate("密码");
                    if (errors.length > 0) {
                        new IInformationFrame("登录失败", errors[0], false).showWindow();
                        buttonPanel.buttonSet[0].setText("登录");
                        return;
                    }
                    String res = authNet.login(usernamePanel.val(), MD5.encode(passwordPanel.val()));
                    if (res.length() > 0) {
                        buttonPanel.buttonSet[0].setText("登录");
                        new IInformationFrame("登录失败", "登录失败：" + res, false).showWindow();
                        return;
                    }
                    masterFrame = new MasterFrame(self);
                    masterFrame.showWindow();
                    this.dispose();
                },
                e -> {
                    usernamePanel.val("");
                    passwordPanel.val("");
                }
        );

        registerButton.addActionListener(e -> {
            new RegisterFrame().showWindow();
            this.dispose();
        });

        forgotPasswordButton.addActionListener(e -> {
            new ForgotPasswordFrame().showWindow();
            this.dispose();
        });
    }
}
