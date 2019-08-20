package top.chorg.window.miniDrawPad.drawing;

import java.awt.*;

public class Circle extends Drawing {//矩形类

    public void draw(Graphics2D g2d) {
        g2d.setPaint(new Color(R, G, B));
        g2d.setStroke(new BasicStroke(stroke));
        g2d.drawOval(Math.min(x1, x2), Math.min(y1, y2), Math.max(Math.abs(x1 - x2),
                Math.abs(y1 - y2)), Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2)));
    }
}
