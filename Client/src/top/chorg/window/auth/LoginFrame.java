package top.chorg.window.auth;

import top.chorg.window.foundation.IFrame;
import top.chorg.window.foundation.IPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginFrame extends IFrame {

    IPanel usernameBase, passwordBase, buttonBase;
    JLabel usernameLabel, passwordLabel;
    JTextField usernameField;
    JPasswordField passwordField;
    JButton registerButton, forgotPasswordButton, resetButton, submitButton;

    public LoginFrame() {
        super(
                340, 180,
                "登录 - iClass",
                new FlowLayout(FlowLayout.CENTER),
                JFrame.EXIT_ON_CLOSE
        );
        this.setLocation(300, 200);
        this.setResizable(false);
    }

    @Override
    public void addComponents() {
        usernameBase = new IPanel(      // Area = 340 * 80
                340, 50,
                new EmptyBorder(15, 20, 0, 20),
                new FlowLayout(FlowLayout.LEFT)
        );
        usernameLabel = new JLabel("用户名");
        usernameLabel.setPreferredSize(new Dimension(40, 25));
        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(140, 25));
        registerButton = new JButton("注册用户");
        usernameBase.addComp(usernameLabel, usernameField, registerButton);


        passwordBase = new IPanel(      // Area = 340 * 80
                340, 40,
                new EmptyBorder(0, 20, 0, 20),
                new FlowLayout(FlowLayout.LEFT)
        );
        passwordLabel = new JLabel("密码");
        passwordLabel.setPreferredSize(new Dimension(40, 25));
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(140, 25));
        forgotPasswordButton = new JButton("忘记密码");
        passwordBase.addComp(passwordLabel, passwordField, forgotPasswordButton);

        buttonBase = new IPanel(
                340, 30,
                null,
                new FlowLayout(FlowLayout.CENTER)
        );
        submitButton = new JButton("登录");
        resetButton = new JButton("重置");
        buttonBase.addComp(submitButton, resetButton);

        this.addComp(usernameBase, passwordBase, buttonBase);

    }

    @Override
    public void addListeners() {

        submitButton.addActionListener(e -> {
            submitButton.setText("登录中...");

        });

        resetButton.addActionListener(e -> {
            usernameField.setText("");
            passwordField.setText("");
        });



    }
}
