package top.chorg.window.index.chatPanel;

import top.chorg.window.foundation.IImageIcon;
import top.chorg.window.foundation.IPanel;

import javax.swing.*;
import java.awt.*;

import static top.chorg.kernel.Variable.resource;

public class IndexChatDisplayPanel extends JScrollPane {

    IPanel master;
    public int width, height;

    public IndexChatDisplayPanel(int width) {
        master = new IPanel(width - 20, 1, null, new FlowLayout(FlowLayout.LEFT));
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

        this.addBubble(
                ChatBubble.BUBBLE_RIGHT, new IImageIcon(resource("defaultUserIcon.png")), "TEST USER",
                "[\"content\",\"{\\\"color\\\":{\\\"value\\\":-16777216,\\\"falpha\\\":0.0},\\\"family\\\":\\\"Lucida Grande\\\",\\\"size\\\":13,\\\"isItalic\\\":false,\\\"isBold\\\":false,\\\"isUnderline\\\":false,\\\"startOff\\\":0,\\\"len\\\":423,\\\"content\\\":\\\"这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试这里是超长消息测试\\\"}\"]\n"
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

    public void addBubble(int direction, IImageIcon avatar, String name, String compiledDoc) {
        master.add(new ChatMessagePane(width - 30, direction, avatar, name, compiledDoc));
    }

}
