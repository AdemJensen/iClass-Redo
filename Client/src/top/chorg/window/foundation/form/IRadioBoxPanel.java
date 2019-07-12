package top.chorg.window.foundation.form;

import top.chorg.window.foundation.IPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class IRadioBoxPanel extends IPanel {

    public IPanel groupPanel;
    public JLabel label;
    public JRadioButton[] buttonSet;
    public ButtonGroup group;

    public IRadioBoxPanel(int width, int height, Border border,
                          int labelWidth, String labelText,
                          int choiceAreaWidth, int choiceAreaHeight, LayoutManager choiceAreaLayout,
                          String...choiceText) {
        super(width, height, border, new FlowLayout(FlowLayout.CENTER));
        groupPanel = new IPanel(choiceAreaWidth, choiceAreaHeight, null, choiceAreaLayout);

        label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(labelWidth, 25));

        buttonSet = new JRadioButton[choiceText.length];
        group = new ButtonGroup();
        for (int i = 0; i < choiceText.length; i++) {
            buttonSet[i] = new JRadioButton(choiceText[i]);
            group.add(buttonSet[i]);
        }
        groupPanel.addComp(buttonSet);
        this.addComp(label, groupPanel);
    }

    public int val() {
        for (int i = 0; i < buttonSet.length; i++) {
            if (buttonSet[i].isSelected()) return i;
        }
        return -1;
    }

    public void val(int val) {
        group.clearSelection();
        buttonSet[val].setSelected(true);
    }

}
