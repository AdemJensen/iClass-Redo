package top.chorg.window.foundation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.HashMap;

public class ILinkedButton extends JButton {

    Font mouseEnteredFont, mouseExitedFont;
    Color mouseEnteredColor, mouseExitedColor;

    public ILinkedButton() {
        setButtonBehavior();
    }

    public ILinkedButton(Icon icon) {
        super(icon);
        setButtonBehavior();
    }

    public ILinkedButton(String text) {
        super(text);
        setButtonBehavior();
    }

    public ILinkedButton(Action a) {
        super(a);
        setButtonBehavior();
    }

    public ILinkedButton(String text, Icon icon) {
        super(text, icon);
        setButtonBehavior();
    }

    private void setButtonBehavior() {
        HashMap<TextAttribute,Object> hm = new HashMap<>();
        hm.put(TextAttribute.FAMILY, ILinkedButton.this.getFont().getFamily()); //字体名称
        hm.put(TextAttribute.SIZE, ILinkedButton.this.getFont().getSize());     //字体大小
        mouseExitedFont = new Font(hm);
        hm.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);            //下滑线
        mouseEnteredFont = new Font(hm);

        mouseEnteredColor = new Color(173, 76, 173);
        mouseExitedColor = new Color(0, 102, 204);

        this.setBorder(null);
        this.setForeground(mouseExitedColor);
        this.setFont(mouseExitedFont);
        this.setBackground(new Color(0, 0, 0, 0));
        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                ILinkedButton.this.setForeground(mouseEnteredColor);
                ILinkedButton.this.setFont(mouseEnteredFont);
                ILinkedButton.this.getTopLevelAncestor().setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ILinkedButton.this.setForeground(mouseExitedColor);
                ILinkedButton.this.setFont(mouseExitedFont);
                ILinkedButton.this.getTopLevelAncestor().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

}
