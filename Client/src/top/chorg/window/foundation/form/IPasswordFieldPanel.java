package top.chorg.window.foundation.form;

import top.chorg.window.foundation.IPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class IPasswordFieldPanel extends IPanel {
    public JLabel label;
    public JPasswordField passwordField;

    public IPasswordFieldPanel(int width, int height, Border border,
                               int labelLength, int textfieldLength, String labelDisplay, String textFiledDisplay) {
        super(width, height, border, new FlowLayout(FlowLayout.CENTER));
        label = new JLabel(labelDisplay);
        passwordField = new JPasswordField(textFiledDisplay);
        label.setPreferredSize(new Dimension(labelLength, 25));
        passwordField.setPreferredSize(new Dimension(textfieldLength, 25));
        this.addComp(label, passwordField);
    }

    public void addActionListener(ActionListener textFieldListener) {
        passwordField.addActionListener(textFieldListener);
    }

    public String val() {
        return passwordField.getText();
    }

    public String val(String val) {
        passwordField.setText(val);
        return passwordField.getText();
    }
    
}
