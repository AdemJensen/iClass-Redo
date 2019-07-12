package top.chorg.window.foundation.form;

import top.chorg.window.foundation.IPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ITextAreaPanel extends IPanel {

    public JLabel label;
    public JTextArea textArea;

    public ITextAreaPanel(int width, int height, Border border, int labelLength,
                          int textfieldWidth, int textfieldHeight, String labelDisplay, String textFiledDisplay) {
        super(width, height, border, new FlowLayout(FlowLayout.CENTER));
        label = new JLabel(labelDisplay);
        textArea = new JTextArea(textFiledDisplay);
        label.setPreferredSize(new Dimension(labelLength, 25));
        textArea.setPreferredSize(new Dimension(textfieldWidth, textfieldHeight));
        this.addComp(label, textArea);
    }

    public String val() {
        return textArea.getText();
    }

    public void val(String val) {
        textArea.setText(val);
    }
}
