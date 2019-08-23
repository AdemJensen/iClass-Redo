package top.chorg.window.foundation.form;

import top.chorg.window.foundation.IPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ICheckBoxPanel extends IPanel {
    public int width;
    public IPanel groupPanel;
    public JLabel label;
    public JCheckBox[] buttonSet;

    public ICheckBoxPanel(int width, Border border, int labelWidth, String labelText,
                          LayoutManager choiceAreaLayout, String...choiceText) {
        super(width, 1, border, new FlowLayout(FlowLayout.CENTER));
        this.width = width;
        label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(labelWidth, 25));

        groupPanel = new IPanel();
        groupPanel.setLayout(choiceAreaLayout);

        buttonSet = new JCheckBox[choiceText.length];
        for (int i = 0; i < choiceText.length; i++) {
            buttonSet[i] = new JCheckBox(choiceText[i]);
        }

        int calcH = calcChoiceAreaHeight(width - labelWidth - 15);
        groupPanel.setPreferredSize(new Dimension(
                width - labelWidth - 15, calcH
        ));
        groupPanel.addComp(buttonSet);
        this.addComp(label, groupPanel);

        this.setPreferredSize(new Dimension(width, calcH + 10));
    }

    private int calcChoiceAreaHeight(int width) {
        int curWidth = 5;
        int height = 5;
        if (buttonSet.length == 0) return 10;
        int unitHeight = buttonSet[0].getPreferredSize().height;
        height += unitHeight + 5;
        for (JCheckBox button : buttonSet) {
            int cw = button.getPreferredSize().width + 5;
            if (curWidth + cw > width) {
                curWidth = 10;
                height += unitHeight + 5;
            }
            curWidth += cw;
        }
        return height;
    }

    public int getTargetHeight() {
        return calcChoiceAreaHeight(width + 10);
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
