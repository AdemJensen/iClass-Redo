package top.chorg.window.foundation;

import top.chorg.support.RenderUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class IColorChooserButton extends JButton {

    private Color color;
    private int width, height;
    private ActionListener enforce;

    public IColorChooserButton(int width, int height, Color starterColor) {
        this(width, height, starterColor, e -> { });
    }

    public IColorChooserButton(int width, int height, Color starterColor, ActionListener enforce) {
        super();
        setModel(new DefaultButtonModel());
        this.height = height;
        this.width = width;
        this.enforce = enforce;
        init(null, getButtonIcon(width, height, starterColor));
        this.setPreferredSize(new Dimension(width, height));
        this.setBorder(null);
        //settingsButton.setBackground(new Color(0, 0, 0, 0));
        this.setOpaque(false);
        color = starterColor;
        this.addDefaultListener();

    }

    private IImageIcon getButtonIcon(int w, int h, Color color) {
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = output.createGraphics();
        RenderUtils.applyQualityRenderingHints(g2);
        g2.setColor(color);
        g2.fillPolygon(new int[]{0, w, w, 0}, new int[]{0, 0, h, h}, 4); // TODO: justify
        g2.dispose();
        return new IImageIcon(output);
    }

    public void addDefaultListener() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Color chosenColor = JColorChooser.showDialog(
                        IColorChooserButton.this, "选择颜色", color
                );
                if (chosenColor != null) {
                    setSelectedColor(chosenColor);
                    enforce.actionPerformed(new ActionEvent(IColorChooserButton.this, 0, ""));
                }
            }
        });
    }

    public Color getSelectedColor() {
        return color;
    }

    public void setSelectedColor(Color color) {
        this.color = color;
        this.setIcon(getButtonIcon(width, height, color));
        this.revalidate();
        this.repaint();
    }

}
