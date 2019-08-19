package top.chorg.window.foundation;

import top.chorg.support.FileUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class IImageIcon extends ImageIcon {

    private String fileName;
    private Image original;

    public String getFileName() {
        return fileName;
    }

    public boolean saveImage(String path) {
        if (fileName != null) {
            return FileUtils.copyFile(fileName, path);
        } else {
            if (original == null) original = this.getImage();
            BufferedImage bufferedImage = toBufferedImage(this.getImage());
            File destination = new File(path);
            if (!destination.mkdirs()) return false;
            try {
                ImageIO.write(bufferedImage, "png", destination);
            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }

    public IImageIcon(String filename, String description) {
        super(filename, description);
        this.fileName = filename;
    }

    public IImageIcon(String filename) {
        super(filename);
        this.fileName = filename;
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

    public IImageIcon(ImageIcon imageIcon) {
        super();
        loadImage(imageIcon.getImage());
    }

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

    public void gray() {
        BufferedImage image = toBufferedImage(this.getImage());

        int w = image.getWidth();
        int h = image.getHeight();

        BufferedImage grayImage = new BufferedImage(w, h, image.getType());
        //BufferedImage grayImage = new BufferedImage(width, height,  BufferedImage.TYPE_BYTE_GRAY);
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int color = image.getRGB(i, j);
                final int r = (color >> 16) & 0xff;
                final int g = (color >> 8) & 0xff;
                final int b = color & 0xff;
                int gray = (int) (0.3 * r + 0.59 * g + 0.11 * b);//加权法灰度化
                //System.out.println("像素坐标：" + " x=" + i + "   y=" + j + "   灰度值=" + gray);
                grayImage.setRGB(i, j, colorToRGB(0, gray, gray, gray));
            }
        }
        this.setImage(grayImage.getScaledInstance(w, h, Image.SCALE_SMOOTH));
    }

    public static int colorToRGB(int alpha, int red, int green, int blue) {
        int newPixel = 0;
        newPixel += alpha;
        newPixel = newPixel << 8;
        newPixel += red;
        newPixel = newPixel << 8;
        newPixel += green;
        newPixel = newPixel << 8;
        newPixel += blue;
        return newPixel;
    }

    public static void transferImage(BufferedImage input, BufferedImage output) {
        for (int i= 0 ; i < input.getWidth() ; i++){
            for (int j = 0 ; j < input.getHeight(); j++){
                int rgb = input.getRGB(i, j);
                input.setRGB(i, j, rgb);
            }
        }
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

    public void setMaximumSize(int width, int height) {
        BufferedImage image = toBufferedImage(this.getImage());
        int w = image.getWidth();
        int h = image.getHeight();
        if (w > width) {
            h = (int) (((double) width / w) * h);
            w = width;
        }
        if (h > height) {
            w = (int) (((double) height / h) * w);
            h = height;
        }
        this.setSize(w, h);
    }

    public boolean isValid() {
        return getIconHeight() > 0 && getIconWidth() > 0;
    }
}
