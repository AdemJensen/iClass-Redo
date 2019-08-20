package top.chorg.window.miniDrawPad.drawing;

import java.awt.*;

public class FilledRect extends Drawing {//实心矩形类

    public void draw(Graphics2D g2d) {
        g2d.setPaint(new Color(R, G, B));
        g2d.setStroke(new BasicStroke(stroke));
        g2d.fillRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
    }
}
