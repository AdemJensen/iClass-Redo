package top.chorg.window.foundation.form;

import top.chorg.window.foundation.IPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class ITextFieldPanel extends IPanel {
    public JLabel label;
    public JTextField textField;

    public ITextFieldPanel(int width, int height, Border border,
                           int labelLength, int textfieldLength, String labelDisplay, String textFiledDisplay) {
        super(width, height, border, new FlowLayout(FlowLayout.CENTER));
        label = new JLabel(labelDisplay);
        textField = new JTextField(textFiledDisplay);
        label.setPreferredSize(new Dimension(labelLength, 25));
        textField.setPreferredSize(new Dimension(textfieldLength, 25));
        this.addComp(label, textField);
    }

    public void addActionListener(ActionListener textFieldListener) {
        textField.addActionListener(textFieldListener);
    }

    public String val() {
        return textField.getText();
    }

    public void val(String val) {
        textField.setText(val);
    }

}
