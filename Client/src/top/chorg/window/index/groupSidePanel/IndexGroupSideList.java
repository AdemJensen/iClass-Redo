package top.chorg.window.index.groupSidePanel;

import top.chorg.kernel.foundation.enumClass.UserLevel;
import top.chorg.window.foundation.IImageIcon;
import top.chorg.window.foundation.IPanel;

import javax.swing.*;
import java.awt.*;

import static top.chorg.kernel.Variable.resource;

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
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.setBackground(new Color(0xf6f6f6));
        master.setBackground(new Color(0xf6f6f6));
        //master.add();
        this.addItem(new ChatListLabel(width - 10, 30,
                new IImageIcon(resource("defaultUserIcon.png")),
                "TEST", UserLevel.SUPER_MANAGER, true));
        this.addItem(new ChatListLabel(width - 10, 30,
                new IImageIcon(resource("defaultUserIcon.png")),
                "TEST", UserLevel.MANAGER, true));
        this.addItem(new ChatListLabel(width - 10, 30,
                new IImageIcon(resource("defaultUserIcon.png")),
                "TEST", UserLevel.NORMAL, true));
        this.addItem(new ChatListLabel(width - 10, 30,
                new IImageIcon(resource("defaultUserIcon.png")),
                "TEST", UserLevel.NORMAL, true));
        this.addItem(new ChatListLabel(width - 10, 30,
                new IImageIcon(resource("defaultUserIcon.png")),
                "TEST", UserLevel.NORMAL, false));
        this.addItem(new ChatListLabel(width - 10, 30,
                new IImageIcon(resource("defaultUserIcon.png")),
                "TEST", UserLevel.NORMAL, false));
        this.addItem(new ChatListLabel(width - 10, 30,
                new IImageIcon(resource("defaultUserIcon.png")),
                "TEST", UserLevel.NORMAL, false));
        this.addItem(new ChatListLabel(width - 10, 30,
                new IImageIcon(resource("defaultUserIcon.png")),
                "TEST", UserLevel.NORMAL, false));
        this.addItem(new ChatListLabel(width - 10, 30,
                new IImageIcon(resource("defaultUserIcon.png")),
                "TEST", UserLevel.NORMAL, false));
        this.addItem(new ChatListLabel(width - 10, 30,
                new IImageIcon(resource("defaultUserIcon.png")),
                "TEST", UserLevel.NORMAL, false));
        this.addItem(new ChatListLabel(width - 10, 30,
                new IImageIcon(resource("defaultUserIcon.png")),
                "TEST", UserLevel.NORMAL, false));
        this.addItem(new ChatListLabel(width - 10, 30,
                new IImageIcon(resource("defaultUserIcon.png")),
                "TEST", UserLevel.NORMAL, false));
        this.addItem(new ChatListLabel(width - 10, 30,
                new IImageIcon(resource("defaultUserIcon.png")),
                "TEST", UserLevel.NORMAL, false));
        this.addItem(new ChatListLabel(width - 10, 30,
                new IImageIcon(resource("defaultUserIcon.png")),
                "TEST", UserLevel.NORMAL, false));
        this.addItem(new ChatListLabel(width - 10, 30,
                new IImageIcon(resource("defaultUserIcon.png")),
                "TEST", UserLevel.NORMAL, false));
        this.addItem(new ChatListLabel(width - 10, 30,
                new IImageIcon(resource("defaultUserIcon.png")),
                "TEST", UserLevel.NORMAL, false));
        this.addItem(new ChatListLabel(width - 10, 30,
                new IImageIcon(resource("defaultUserIcon.png")),
                "TEST", UserLevel.NORMAL, false));
        this.addItem(new ChatListLabel(width - 10, 30,
                new IImageIcon(resource("defaultUserIcon.png")),
                "TEST", UserLevel.NORMAL, false));
        this.addItem(new ChatListLabel(width - 10, 30,
                new IImageIcon(resource("defaultUserIcon.png")),
                "TEST", UserLevel.NORMAL, false));
        this.addItem(new ChatListLabel(width - 10, 30,
                new IImageIcon(resource("defaultUserIcon.png")),
                "TEST", UserLevel.NORMAL, false));
        this.setViewportView(master);
    }
//
//    public Component addImpl(Component comp, int index) {
//        master.add(comp, index);
//        height += 55;
//        master.setPreferredSize(new Dimension(width, height));
//        return comp;
//    }

    public void addItem(ChatListLabel comp) {
        this.addItem(comp, -1);
    }

    public void addItem(ChatListLabel comp, int index) {
        master.add(comp);
        height += comp.getPreferredSize().height + 5;
        master.setPreferredSize(new Dimension(width, height));
    }
}
