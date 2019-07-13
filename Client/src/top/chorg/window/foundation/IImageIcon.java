package top.chorg.window.foundation;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.net.URL;

public class IImageIcon extends ImageIcon {

    public IImageIcon(String filename, String description) {
        super(filename, description);
    }

    public IImageIcon(String filename) {
        super(filename);
    }

    public IImageIcon(URL location, String description) {
        super(location, description);
    }

    public IImageIcon(URL location) {
        super(location);
    }

    public IImageIcon(Image image, String description) {
        super(image, description);
    }

    public IImageIcon(Image image) {
        super(image);
    }

    public IImageIcon(byte[] imageData, String description) {
        super(imageData, description);
    }

    public IImageIcon(byte[] imageData) {
        super(imageData);
    }

    public IImageIcon() { super(); }

    public void setSize(int width, int height) {
        this.setImage(this.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }

    public void setRadius(int radius) {
        BufferedImage image = toBufferedImage(this.getImage());
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = output.createGraphics();
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, radius, radius));
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        this.setImage(output.getScaledInstance(w, h, Image.SCALE_SMOOTH));
    }


    public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage)image;
        }
        image = new ImageIcon(image).getImage();
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            int transparency = Transparency.OPAQUE;
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(
                    image.getWidth(null), image.getHeight(null), transparency);
        } catch (HeadlessException ignored) { }

        if (bimage == null) {
            int type = BufferedImage.TYPE_INT_RGB;
            bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        }
        Graphics g = bimage.createGraphics();

        g.drawImage(image, 0, 0, null);
        g.dispose();

        return bimage;
    }
}
