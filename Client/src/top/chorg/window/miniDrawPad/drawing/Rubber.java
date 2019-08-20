package top.chorg.window.miniDrawPad.drawing;

import java.awt.*;

public class Rubber extends Drawing {//橡皮擦类

    public void draw(Graphics2D g2d) {
        g2d.setPaint(new Color(255, 255, 255));//白色
        g2d.setStroke(new BasicStroke(stroke + 4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
        g2d.drawLine(x1, y1, x2, y2);
    }
}
