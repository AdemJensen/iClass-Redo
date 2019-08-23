package top.chorg.window.foundation.form;

import top.chorg.window.foundation.IPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class IRadioBoxPanel extends IPanel {

    int width;
    public IPanel groupPanel;
    public JLabel label;
    public JRadioButton[] buttonSet;
    public ButtonGroup group;

    public IRadioBoxPanel(int width, Border border, int labelWidth, String labelText,
                          LayoutManager choiceAreaLayout, String...choiceText) {
        super(width, 1, border, new FlowLayout(FlowLayout.CENTER));
        this.width = width;
        label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(labelWidth, 25));

        groupPanel = new IPanel();
        groupPanel.setLayout(choiceAreaLayout);

        buttonSet = new JRadioButton[choiceText.length];
        group = new ButtonGroup();
        for (int i = 0; i < choiceText.length; i++) {
            buttonSet[i] = new JRadioButton(choiceText[i]);
            group.add(buttonSet[i]);
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
        for (JRadioButton button : buttonSet) {
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

    public int val() {
        for (int i = 0; i < buttonSet.length; i++) {
            if (buttonSet[i].isSelected()) return i;
        }
        return -1;
    }

    public void val(int val) {
        group.clearSelection();
        if (val >= 0) buttonSet[val].setSelected(true);
    }

}
