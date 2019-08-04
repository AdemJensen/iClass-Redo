package top.chorg.window.index;

import top.chorg.window.foundation.IPanel;

import javax.swing.*;
import java.awt.*;

public class IndexChatList extends JScrollPane {

    public IPanel master;

    public IndexChatList() {
        master = new IPanel(
                210, 500,
                null,
                new FlowLayout(FlowLayout.CENTER)
        );

        //master.add();

        this.setViewportView(master);
    }

}
