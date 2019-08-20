package top.chorg.window.miniDrawPad;

import top.chorg.support.RenderUtils;
import top.chorg.window.foundation.IImageIcon;
import top.chorg.window.miniDrawPad.drawing.Drawing;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

//文件类 （文件的打开、新建、保存）
public class FileClass {
    private DrawPad drawpad;
    DrawArea drawarea;

    FileClass(DrawPad dp, DrawArea da) {
        drawpad = dp;
        drawarea = da;
    }

    public void newFile() {
        // 新建图像
        drawarea.setIndex(0);
        drawarea.setCurrentChoice(3);//设置默认为随笔画
        drawarea.setColor(Color.black);//设置颜色
        drawarea.setStroke(1.0f);//设置画笔的粗细
        drawarea.createNewItem();
        drawarea.repaint();
    }

    public void openFile() {
        // 打开画板文件

        //JFileChooser 为用户选择文件提供了一种简单的机制
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "MiniDrawPad Savings", "xxh");//其中只显示.xxh文件
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(drawpad);

        if (returnVal == JFileChooser.CANCEL_OPTION) { //如果单击确定按钮就执行下面得程序
            return;
        }
        File fileName = fileChooser.getSelectedFile();//getSelectedFile()返回选中的文件
        if (!fileName.canRead()) {
            JOptionPane.showMessageDialog(
                    fileChooser, "错误", "无此文件读权限，请稍后再试或联系您的管理员", JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        if (fileName.getName().equals("")) { //文件名不存在时
            JOptionPane.showMessageDialog(fileChooser, "错误", "请输入文件名", JOptionPane.ERROR_MESSAGE);
        } else {

            try {
                FileInputStream ifs = new FileInputStream(fileName);
                ObjectInputStream input = new ObjectInputStream(ifs);

                Drawing inputRecord;
                int countNumber = input.readInt();
                for (int i = 0; i < countNumber; i++) {
                    drawarea.setIndex(i);
                    inputRecord = (Drawing) input.readObject();
                    drawarea.itemList[i] = inputRecord;
                }
                drawarea.createNewItem();
                input.close();
                drawarea.repaint();
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(drawpad, "没有找到源文件！", "没有找到源文件", JOptionPane.ERROR_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(drawpad, "读文件时发生错误！", "读取错误", JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException e) {
                JOptionPane.showMessageDialog(drawpad, "不能创建对象！", "已到文件末尾", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    //保存图像文件程序段，用到文件对象流（FileOutputStream）
    public void saveFile() {
        // 保存图像

        //JFileChooser 为用户选择文件提供了一种简单的机制
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        //setFileSelectionMode()设置 JFileChooser，以允许用户只选择文件、只选择目录，或者可选择文件和目录。
        int result = fileChooser.showSaveDialog(drawpad);
        if (result == JFileChooser.CANCEL_OPTION) {
            return;
        }

        File fileName = fileChooser.getSelectedFile();//getSelectedFile()返回选中的文件
        //测试应用程序是否可以修改此抽象路径名表示的文件
        if (fileName.getName().equals("")) { //文件名不存在时
            JOptionPane.showMessageDialog(fileChooser, "错误", "请输入文件名", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                if (fileName.delete()) throw new Exception("无法覆盖已有文件");
                //删除此抽象路径名表示的文件或目录
                FileOutputStream fos = new FileOutputStream(fileName + ".xxh");//文件输出流以字节的方式输出
                //对象输出流
                ObjectOutputStream output = new ObjectOutputStream(fos);
                //Drawing record;

                output.writeInt(drawarea.getIndex());

                for (int i = 0; i < drawarea.getIndex(); i++) {
                    Drawing p = drawarea.itemList[i];
                    output.writeObject(p);
                    output.flush();//刷新该流的缓冲。此操作将写入所有已缓冲的输出字节，并将它们刷新到底层流中。
                    //将所有的图形信息强制的转换成父类线性化存储到文件中
                }
                output.close();
                fos.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        fileChooser, "错误",
                        "出现错误：" + e.getMessage() + "，请稍后再试或联系您的管理员",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    public void exportFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showSaveDialog(drawpad);
        if (result == JFileChooser.CANCEL_OPTION) {
            return;
        }

        File fileName = fileChooser.getSelectedFile();//getSelectedFile()返回选中的文件
        //测试应用程序是否可以修改此抽象路径名表示的文件
        if (fileName.getName().equals("")) { //文件名不存在时
            JOptionPane.showMessageDialog(fileChooser, "错误", "请输入文件名", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                if (fileName.delete()) throw new Exception("无法覆盖已有文件");
                IImageIcon imageIcon = generateImageIcon();
                imageIcon.saveImage(fileName.getPath() + ".png");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        fileChooser, "错误",
                        "出现错误：" + e.getMessage() + "，请稍后再试或联系您的管理员",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    public IImageIcon generateImageIcon() {
        Toolkit tool = drawpad.getToolkit();//得到一个Toolkit类的对象（主要用于得到屏幕的大小）
        Dimension dim = tool.getScreenSize();//得到屏幕的大小 （返回Dimension对象）
        BufferedImage image = new BufferedImage( dim.width - 70, dim.height - 100, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = image.createGraphics();
        RenderUtils.applyQualityRenderingHints(graphics2D);
        graphics2D.setPaint(Color.WHITE);
        graphics2D.fillRect(0, 0, dim.width - 70, dim.height - 100);
        for (int i = 0; i < drawarea.getIndex(); i++) {
            Drawing p = drawarea.itemList[i];
            p.draw(graphics2D);
        }
        graphics2D.dispose();
        return new IImageIcon(image);
    }

}
