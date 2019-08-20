package top.chorg.window.miniDrawPad.drawing;

import java.awt.*;

public class Pencil extends Drawing {//随笔画类

    public void draw(Graphics2D g2d) {
        g2d.setPaint(new Color(R, G, B));
        g2d.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
        g2d.drawLine(x1, y1, x2, y2);
    }
}
