package top.chorg.window.miniDrawPad;

import javax.swing.*;

//帮助菜单功能的事项类
public class Help extends JFrame {
    private DrawPad drawpad;

    Help(DrawPad dp) {
        drawpad = dp;
    }

    public void MainHelp() {
        JOptionPane.showMessageDialog(
                this, "MiniDrawPad帮助文档！",
                "MiniDrawPad", JOptionPane.INFORMATION_MESSAGE
        );
    }

    public void AboutBook() {
        JOptionPane.showMessageDialog(
                drawpad,
                "MiniDrawPad\n\t版本：2.0.0\n\t原始作者：刘军光\n升级作者：伊米哇嘎乃\n" +
                        "原作时间：2009/12/13\n升级时间：2019/08/20",
                "MiniDrawPad",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
