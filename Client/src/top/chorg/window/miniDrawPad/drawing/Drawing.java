package top.chorg.window.miniDrawPad.drawing;

import java.awt.*;
import java.io.Serializable;
//图形绘制类 用于绘制各种图形
//父类，基本图形单元，用到串行的接口，保存使用到
//公共的属性放到超类中，子类可以避免重复定义

/*类通过实现 java.io.Serializable 接口以启用其序列化功能。
未实现此接口的类将无法使其任何状态序列化或反序列化。
可序列化类的所有子类型本身都是可序列化的。序列化接口没有方法或字段，
仅用于标识可序列化的语义。*/


public abstract class Drawing implements Serializable {

    public int x1, x2, y1, y2;         //定义坐标属性
    public int R, G, B;                //定义色彩属性
    public float stroke;               //定义线条粗细的属性
    public int type;                   //定义字体属性
    public String s1;                  //定义字体的风格
    public String s2;                  //定义字体的风格

    public abstract void draw(Graphics2D g2d); //定义绘图方法
}