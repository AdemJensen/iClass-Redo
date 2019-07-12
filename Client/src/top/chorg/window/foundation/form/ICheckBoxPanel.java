package top.chorg.window.foundation.form;

import top.chorg.window.foundation.IPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ICheckBoxPanel extends IPanel {
    public IPanel groupPanel;
    public JLabel label;
    public JCheckBox[] buttonSet;

    public ICheckBoxPanel(int width, int height, Border border,
                          int labelWidth, String labelText,
                          int choiceAreaWidth, int choiceAreaHeight, LayoutManager choiceAreaLayout,
                          String...choiceText) {
        super(width, height, border, new FlowLayout(FlowLayout.CENTER));
        groupPanel = new IPanel(choiceAreaWidth, choiceAreaHeight, null, choiceAreaLayout);

        label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(labelWidth, 25));

        buttonSet = new JCheckBox[choiceText.length];
        for (int i = 0; i < choiceText.length; i++) {
            buttonSet[i] = new JCheckBox(choiceText[i]);
        }
        groupPanel.addComp(buttonSet);
        this.addComp(label, groupPanel);
    }

    public int[] val() {
        int selection = 0;
        for (JCheckBox jCheckBox : buttonSet) {
            if (jCheckBox.isSelected()) selection++;
        }
        int[] result = new int[selection];
        selection = 0;
        for (int i = 0; i < buttonSet.length; i++) {
            if (buttonSet[i].isSelected()) {
                result[selection] = i;
                selection++;
            }
        }
        return result;
    }

    public void val(int...val) {
        for (JCheckBox checkBox : buttonSet) {
            checkBox.setSelected(false);
        }
        for (int value : val) {
            buttonSet[value].setSelected(true);
        }
    }
}
