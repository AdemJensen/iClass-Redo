package top.chorg.window.index.groupSidePanel;

import top.chorg.window.foundation.IImageIcon;
import top.chorg.window.foundation.IPanel;

import javax.swing.*;
import java.awt.*;

public class IndexGroupSideList extends JScrollPane {

    public IPanel master;
    public int width = 260, height = 5;

    public IndexGroupSideList() {
        master = new IPanel(
                width, 0,
                null,
                new FlowLayout(FlowLayout.LEFT)
        );
        this.setPreferredSize(new Dimension(270, 400));
        this.setBorder(null);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.setBackground(new Color(0xf6f6f6));
        master.setBackground(new Color(0xf6f6f6));
        this.setViewportView(master);
    }

    public void clearList() {
        master.removeAll();
        height = 5;
        master.setPreferredSize(new Dimension(width, height));
        this.revalidate();
    }

    public void addItem(int userId, IImageIcon icon, String name, int userLevel, boolean isOnline) {
        this.addItem(new ChatListLabel(width - 10, 30, userId, icon, name, userLevel, isOnline));
    }

    public void addItem(ChatListLabel comp) {
        this.addItem(comp, -1);
    }

    public void addItem(ChatListLabel comp, int index) {
        master.add(comp);
        height += comp.getPreferredSize().height + 5;
        master.setPreferredSize(new Dimension(width, height));
    }
}
