package top.chorg.window.foundation;

import javax.swing.*;
import java.awt.*;

public class IFrame extends JFrame {

    public IFrame(int width, int height, String title, LayoutManager layout, int constant) {
        this.setSize(new Dimension(width, height));
        if (title == null) this.setTitle("NULL");
        else this.setTitle(title);
        this.setLayout(layout);
        this.setDefaultCloseOperation(constant);
        this.addComponents();
        this.addListeners();
    }

    public IFrame(int width, int height, String title, LayoutManager layout) {
        this(width, height, title, layout, JFrame.DISPOSE_ON_CLOSE);
    }

    public IFrame(int width, int height, String title, int constant) {
        this(width, height, title, null, constant);
    }

    public IFrame(int width, int height, String title) {
        this(width, height, title, null, JFrame.DISPOSE_ON_CLOSE);
    }

    public void addComp(Component...components) {
        for (Component component : components) {
            this.add(component);
        }
    }

    public void addComponents() { }
    public void addListeners() { }

    public void showWindow() {
        //this.pack();
        this.setVisible(true);
    }

    public void hideWindow() {
        this.setVisible(false);
    }

}
