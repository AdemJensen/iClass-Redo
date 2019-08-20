package top.chorg.window.miniDrawPad;

import top.chorg.support.FileUtils;
import top.chorg.window.foundation.notice.IInformationFrame;

import javax.swing.*;

import static top.chorg.kernel.Variable.resource;

//帮助菜单功能的事项类
public class Help extends JFrame {
    private DrawPad drawpad;

    Help(DrawPad dp) {
        drawpad = dp;
    }

    public void MainHelp() {
        String help = FileUtils.readFileContent(resource("miniDrawPad", "help.txt"));
        if (help == null) help = "错误：无法获取帮助";
        new IInformationFrame("帮助", help, true).showWindow();
    }

    public void AboutBook() {
        JOptionPane.showMessageDialog(
                drawpad,
                "MiniDrawPad\n\t版本：2.0.0\n\t原始作者：刘军光\n升级作者：伊米哇嘎乃\n" +
                        "原作时间：2009/12/13\n升级时间：2019/08/20",
                "关于",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
