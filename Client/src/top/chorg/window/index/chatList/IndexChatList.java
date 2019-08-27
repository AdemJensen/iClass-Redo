package top.chorg.window.index.chatList;

import top.chorg.window.foundation.IPanel;

import javax.swing.*;
import java.awt.*;

public class IndexChatList extends JScrollPane {

    public IPanel master;
    public int width = 260, height = 5;

    public IndexChatList() {
        master = new IPanel(
                width, height,
                null,
                new FlowLayout(FlowLayout.LEFT)
        );
        this.setBorder(null);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.setBackground(new Color(0xf6f6f6));
        master.setBackground(new Color(0xf6f6f6));

        this.setViewportView(master);
    }

    public void resetHeight() {
        height = 5;
        for (Component component : master.getComponents()) {
            height += component.getPreferredSize().height + 5;
        }
        master.setPreferredSize(new Dimension(width, height));
    }

    public void addItem(IndexChatLabel comp) {
        this.addItem(comp, -1);
    }

    public void addItem(IndexChatLabel comp, int index) {
        master.add(comp, index);
        height += comp.getPreferredSize().height;
        master.setPreferredSize(new Dimension(width, height));
    }

    public void remove(IndexChatLabel comp) {
        if (!this.master.isAncestorOf(comp)) return;
        master.remove(comp);
        height -= comp.getPreferredSize().height;
        master.setPreferredSize(new Dimension(width, height));
        this.revalidate();
    }

}
