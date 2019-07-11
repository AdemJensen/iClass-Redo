package top.chorg.window.foundation.form;

import top.chorg.window.foundation.IPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class IFormButtonPanel extends IPanel {

    public JButton[] buttonSet;

    public IFormButtonPanel(int width, int height, Border border, String...buttonText) {
        super(width, height, border, new FlowLayout(FlowLayout.CENTER));
        buttonSet = new JButton[buttonText.length];
        for (int i = 0; i < buttonText.length; i++) {
            buttonSet[i] = new JButton(buttonText[i]);
        }
        this.addComp(buttonSet);
    }

    public void addActionListeners(ActionListener...listeners) {
        if (buttonSet.length != listeners.length) {
            throw new IllegalArgumentException(
                    String.format("Expected %d listeners but %d found", buttonSet.length, listeners.length)
            );
        }
        for (int i = 0; i < buttonSet.length; i++) {
            buttonSet[i].addActionListener(listeners[i]);
        }
    }

}
