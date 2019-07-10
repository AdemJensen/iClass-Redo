package top.chorg.window.auth;

import top.chorg.window.foundation.IFrame;

import javax.swing.*;
import java.awt.*;

public class registerFrame extends IFrame {
    public registerFrame() {
        super(
                340, 180,
                "注册用户 - iClass",
                new FlowLayout(FlowLayout.CENTER),
                JFrame.DISPOSE_ON_CLOSE
        );
        this.addComponents();
        this.setLocation(300, 200);
    }
}
