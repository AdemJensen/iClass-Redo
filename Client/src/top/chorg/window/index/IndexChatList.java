package top.chorg.window.index;

import top.chorg.kernel.api.UserInfo;
import top.chorg.window.foundation.IPanel;

import javax.swing.*;
import java.awt.*;

public class IndexChatList extends JScrollPane {

    public IPanel master;
    public int width = 260, height = 0;

    public IndexChatList() {
        master = new IPanel(
                width, height,
                null,
                new FlowLayout(FlowLayout.LEFT)
        );
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //master.add();
        this.addItem(new UserIndexChatLabel(width - 20, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 2, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 22, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 2, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 2, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 2, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 22, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 2, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 22, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 2, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 22, 1
        )));
        this.addItem(new UserIndexChatLabel(width - 20, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 2, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 22, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 2, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 2, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 2, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 22, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 2, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 22, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 2, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 22, 1
        )));
        this.addItem(new UserIndexChatLabel(width - 20, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 2, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 22, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 2, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 2, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 2, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 22, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 2, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 22, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 2, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 22, 1
        )));
        this.addItem(new UserIndexChatLabel(width - 20, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 2, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 22, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 2, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 2, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 2, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 22, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 2, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 22, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 2, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 22, 1
        )));
        this.addItem(new UserIndexChatLabel(width - 20, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 2, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 22, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 2, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 2, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 2, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 22, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 2, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 22, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 2, 1
        )));
        this.addItem(new UserIndexChatLabel(240, 45, new UserInfo(
                "TUN", "TRN", "233333333333", "test@test.com", "12345678901", 22, 1
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
        height += 50;
        master.setPreferredSize(new Dimension(width, height));
    }

}
