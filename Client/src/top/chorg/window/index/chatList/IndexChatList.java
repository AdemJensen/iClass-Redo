package top.chorg.window.index.chatList;

import top.chorg.kernel.api.UserInfo;
import top.chorg.window.foundation.IPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

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
        //master.add();
        this.addItem(new UserIndexChatLabel(width - 20, 45, new UserInfo(
                "TUN1", new Date(), "TRN", "233333333333", "test@test.com", "12345678901", "1000000000",  2, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", new Date(), "TRN", "233333333333", "test@test.com", "12345678901", "1000000000", 22, 1
        )));

        this.setViewportView(master);
    }
//
//    public Component addImpl(Component comp, int index) {
//        master.add(comp, index);
//        height += 55;
//        master.setPreferredSize(new Dimension(width, height));
//        return comp;
//    }

    public void addItem(IndexChatLabel comp) {
        this.addItem(comp, -1);
    }

    public void addItem(IndexChatLabel comp, int index) {
        master.add(comp);
        // height += 50;

        height += comp.getPreferredSize().height;
        // System.out.println("PREFER:" + comp.getPreferredSize().height);
        master.setPreferredSize(new Dimension(width, height));
    }

}
