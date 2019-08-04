package top.chorg.window.foundation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IClickableAdapter extends MouseAdapter {

    JComponent component;
    boolean isOriginallyOpaque;
    Color originColor;

    public IClickableAdapter(JComponent component) {
        this.component = component;
        this.isOriginallyOpaque = component.isOpaque();
        this.originColor = component.getBackground();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        component.setBackground(new Color(
                (int) (originColor.getRed() * 0.8),
                (int) (originColor.getGreen() * 0.8),
                (int) (originColor.getBlue() * 0.8)
        ));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        component.setBackground(new Color(
                (int) (originColor.getRed() * 0.9),
                (int) (originColor.getGreen() * 0.9),
                (int) (originColor.getBlue() * 0.9)
        ));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (!isOriginallyOpaque) component.setOpaque(true);
        component.setBackground(new Color(
                (int) (originColor.getRed() * 0.9),
                (int) (originColor.getGreen() * 0.9),
                (int) (originColor.getBlue() * 0.9)
        ));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (!isOriginallyOpaque) component.setOpaque(false);
        component.setBackground(originColor);
    }

}
