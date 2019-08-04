package top.chorg.window.foundation.button;

import javax.swing.*;
import java.awt.*;

public class ICapsuleButton extends JButton {

    public Color foregroundColor, backgroundColor;

    public ICapsuleButton(int width, int height, String text, Color foregroundColor, Color backgroundColor) {
        super(text);
        this.backgroundColor = backgroundColor;
        this.setPreferredSize(new Dimension(width, height));
        this.setForeground(foregroundColor);
        this.setBackground(backgroundColor);
        // TODO: Rounded corner.
    }

}
