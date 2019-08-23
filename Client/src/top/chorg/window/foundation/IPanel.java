package top.chorg.window.foundation;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class IPanel extends JPanel {
    public IPanel() { }

    public IPanel(int width, int height, Border border, LayoutManager layout) {
        this.setPreferredSize(new Dimension(width, height));
        if (border != null) this.setBorder(border);
        this.setLayout(layout);
    }

    @Override
    public void setBackground(Color bg) {
        this.setOpaque(true);
        super.setBackground(bg);
    }

    public IPanel(int width, int height, Border border) {
        this(width, height, border, null);
    }

    public IPanel(int width, int height) {
        this(width, height, null, null);
    }

    public void addComp(Component...components) {
        for (Component component : components) {
            this.add(component);
        }
    }

}
