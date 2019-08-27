package top.chorg.window.index.chatPanel;

import top.chorg.support.TimeUtils;
import top.chorg.window.foundation.IImageIcon;
import top.chorg.window.foundation.IPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

import static top.chorg.kernel.Variable.resource;

public class IndexChatDisplayPanel extends JScrollPane {

    IPanel master;
    public int width, height;

    public IndexChatDisplayPanel(int width) {
        master = new IPanel(width - 20, 1, null, new FlowLayout(FlowLayout.CENTER));
        master.setBackground(Color.WHITE);
        this.setViewportView(master);
        this.setPreferredSize(new Dimension(width, 420));
        this.width = width;
        this.setBackground(Color.WHITE);
        this.setBorder(null);

        this.addBubble(
                ChatBubble.BUBBLE_LEFT, new IImageIcon(resource("defaultUserIcon.png")), "TEST USER",
                "[\"content\",\"{\\\"color\\\":{\\\"value\\\":-16777216,\\\"falpha\\\":0.0},\\\"family\\\":\\\"Lucida Grande\\\",\\\"size\\\":13,\\\"isItalic\\\":false,\\\"isBold\\\":false,\\\"isUnderline\\\":false,\\\"startOff\\\":0,\\\"len\\\":423,\\\"content\\\":\\\"这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试\\\"}\"]\n"
        );

        this.addTimeLabel(new Date());

        this.addBubble(
                ChatBubble.BUBBLE_RIGHT, new IImageIcon(resource("defaultUserIcon.png")), "TEST USER",
                "[\"icon\",\"fd6746802ea93b5ea345515a91c1a3f1\",\"icon\",\"d131400f9debda3e071b591e326503c9\",\"icon\",\"0b7d24d850104d14cb21e2049e7a1c52\",\"icon\",\"be4db0e91d0fe56d0394b5b4615e33ea\",\"icon\",\"d1fda401566fd58238447599cbac6ad2\",\"icon\",\"4fc86e9a8a4cccda0c047ea58b6a19af\"]"
        );

        resetHeight();

        this.setBackground(Color.WHITE);
    }

    public void resetHeight() {
        int height = 5;
        for (Component component : master.getComponents()) {
            height += component.getPreferredSize().height + 5;
        }
        master.setPreferredSize(new Dimension(width - 20, height));
    }

    public void resetWidth(int width) {
        this.setPreferredSize(new Dimension(width, 420));
        for (Component component : master.getComponents()) {
            if (component instanceof ChatMessagePane) {
                ((ChatMessagePane) component).resetWidth(width - 30);
            }
        }
        resetHeight();
        this.revalidate();
        this.repaint();
    }

    public void resetDisplayArea() {
        this.master.removeAll();
    }

    public void addBubble(int direction, IImageIcon avatar, String name, String compiledDoc) {
        master.add(new ChatMessagePane(width - 30, direction, avatar, name, compiledDoc));
    }

    public void addTimeLabel(Date date) {
        JLabel label = new JLabel(TimeUtils.dateToStrLong(date));
        label.setForeground(Color.LIGHT_GRAY);
        master.add(label);
    }

}
