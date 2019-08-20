package top.chorg.window.miniDrawPad.drawing;

import java.awt.*;

public class Line extends Drawing      // 直线类
{
    public void draw(Graphics2D g2d) {
        g2d.setPaint(new Color(R, G, B));// 为 Graphics2D 上下文设置 Paint 属性。
        // 使用为 null 的 Paint 对象调用此方法对此 Graphics2D 的当前 Paint 属性没有任何影响。

        g2d.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_BEVEL));
        // setStroke(Stroke s)为 Graphics2D 上下文设置 Stroke
        // BasicStroke 类定义针对图形图元轮廓呈现属性的一个基本集合
        // BasicStroke.CAP_ROUND使用半径等于画笔宽度一半的圆形装饰结束未封闭的子路径和虚线线段
        // BasicStroke.JOIN_BEVEL通过直线连接宽体轮廓的外角，将路径线段连接在一起。
        g2d.drawLine(x1, y1, x2, y2);// 画直线

    }
}
