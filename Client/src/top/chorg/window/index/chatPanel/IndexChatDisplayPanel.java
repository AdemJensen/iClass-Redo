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
        this.master.revalidate();
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
