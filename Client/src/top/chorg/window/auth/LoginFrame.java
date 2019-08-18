package top.chorg.window.auth;

import top.chorg.window.foundation.form.IFormButtonPanel;
import top.chorg.window.foundation.IFrame;
import top.chorg.window.foundation.IPanel;
import top.chorg.window.foundation.form.IPasswordFieldPanel;
import top.chorg.window.foundation.form.ITextFieldPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

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
                new FlowLayout(FlowLayout.CENTER),
                JFrame.EXIT_ON_CLOSE
        );
        this.setLocationCenter(340, 180);
        this.setResizable(false);
    }

    @Override
    public void addComponents() {
        usernamePanelBase = new IPanel(
                340, 50,
                new EmptyBorder(15, 20, 0, 20),
                new FlowLayout(FlowLayout.CENTER)
        );
        usernamePanel = new ITextFieldPanel(      // Area = 340 * 80
                190, 30,
                null,
                40, 140,
                "用户名",
                ""
        );
        registerButton = new JButton("注册用户");
        usernamePanelBase.addComp(usernamePanel, registerButton);


        passwordPanelBase = new IPanel(
                340, 40,
                new EmptyBorder(0, 20, 0, 20),
                new FlowLayout(FlowLayout.CENTER)
        );
        passwordPanel = new IPasswordFieldPanel(      // Area = 340 * 80
                190, 30,
                null,
                40, 140,
                "密码",
                ""
        );
        forgotPasswordButton = new JButton("忘记密码");
        passwordPanelBase.addComp(passwordPanel, forgotPasswordButton);

        buttonPanel = new IFormButtonPanel(
                340, 30,
                null,
                "登录", "重置"
        );

        this.addComp(usernamePanelBase, passwordPanelBase, buttonPanel);

    }

    @Override
    public void addListeners() {

        buttonPanel.addActionListeners(
                e -> {
                    buttonPanel.buttonSet[0].setText("登录中...");

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
            //new ForgotPasswordFrame().showWindow();
            this.dispose();
        });
    }
}
