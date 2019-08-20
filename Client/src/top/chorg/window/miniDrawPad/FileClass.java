package top.chorg.window.miniDrawPad;

import top.chorg.support.RenderUtils;
import top.chorg.window.foundation.IImageIcon;
import top.chorg.window.miniDrawPad.drawing.Drawing;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

//文件类 （文件的打开、新建、保存）
public class FileClass {
    private MiniDrawPad drawpad;
    DrawArea drawarea;

    FileClass(MiniDrawPad dp, DrawArea da) {
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

        FileDialog dialog = new FileDialog(new Frame(), "选择文件", FileDialog.LOAD);
        String filePath = engageSelection(dialog);
        if (filePath == null) return;
        try {
            FileInputStream ifs = new FileInputStream(filePath);
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
            JOptionPane.showMessageDialog(
                    drawpad, "没有找到源文件！", "没有找到源文件", JOptionPane.ERROR_MESSAGE
            );
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    drawpad, "读文件时发生错误！", "读取错误", JOptionPane.ERROR_MESSAGE
            );
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(
                    drawpad, "不能创建对象！", "数据文件可能已损坏，请联系管理员", JOptionPane.ERROR_MESSAGE
            );
        }

    }

    //保存图像文件程序段，用到文件对象流（FileOutputStream）
    public void saveFile() {
        // 保存图像

        FileDialog dialog = new FileDialog(new Frame(), "选择保存位置", FileDialog.SAVE);
        String filePath = engageSelection(dialog);
        if (filePath == null) return;
        try {
            if (filePath.toUpperCase().endsWith(".XXH")) filePath = filePath + ".xxh";
            File fileName = new File(filePath);
            if (fileName.exists() && !fileName.delete()) throw new Exception("无法覆盖已有文件");
            //删除此抽象路径名表示的文件或目录
            FileOutputStream fos = new FileOutputStream(fileName);//文件输出流以字节的方式输出
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
                    drawpad, "错误",
                    "出现错误：" + e.getMessage() + "，请稍后再试或联系您的管理员",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private String engageSelection(FileDialog dialog) {
        dialog.setFilenameFilter((dir, name) -> {
            String process = name.toUpperCase();
            return process.endsWith("XXH");
        });
        dialog.setMultipleMode(false);
        dialog.setVisible(true);
        String filePath = dialog.getDirectory() + File.separator + dialog.getFile();
        if (dialog.getFile() == null || dialog.getFile().equals("")) return null;
        else return filePath;
    }

    public void exportFile() {
        FileDialog dialog = new FileDialog(new Frame(), "选择保存位置", FileDialog.SAVE);
        dialog.setFilenameFilter((dir, name) -> {
            String process = name.toUpperCase();
            return process.endsWith("PNG");
        });
        dialog.setMultipleMode(false);
        dialog.setVisible(true);
        String filePath = dialog.getDirectory() + File.separator + dialog.getFile();
        if (dialog.getFile() == null || dialog.getFile().equals("")) return;
        if (!filePath.toUpperCase().endsWith(".png")) filePath = filePath + ".png";
        File fileName = new File(filePath);
        //测试应用程序是否可以修改此抽象路径名表示的文件

        try {
            if (fileName.exists() && !fileName.delete()) throw new Exception("无法覆盖已有文件");
            IImageIcon imageIcon = generateImageIcon();
            imageIcon.saveImage(fileName.getPath());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    drawpad, "错误",
                    "出现错误：" + e.getMessage() + "，请稍后再试或联系您的管理员",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public IImageIcon generateImageIcon() {
        Toolkit tool = drawpad.getToolkit();//得到一个Toolkit类的对象（主要用于得到屏幕的大小）
        Dimension dim = tool.getScreenSize();//得到屏幕的大小 （返回Dimension对象）
        BufferedImage image = new BufferedImage(dim.width - 70, dim.height - 100, BufferedImage.TYPE_INT_RGB);
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
