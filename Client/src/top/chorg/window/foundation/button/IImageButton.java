package top.chorg.window.foundation.button;

import top.chorg.window.foundation.IClickableAdapter;
import top.chorg.window.foundation.IImageIcon;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class IImageButton extends JButton {

    IImageIcon icon;
    int width, height;

    public IImageButton(int width, int height, String dir) {
        super();
        icon = new IImageIcon(dir);
        icon.setSize(width, height);
        setModel(new DefaultButtonModel());
        init(null, icon);
        this.setPreferredSize(new Dimension(width, height));
        this.setBorder(null);
        //settingsButton.setBackground(new Color(0, 0, 0, 0));
        this.setOpaque(false);
        this.addDefaultListener();
    }

    public void addDefaultListener() {
        this.addMouseListener(new IClickableAdapter(this));
    }

}
