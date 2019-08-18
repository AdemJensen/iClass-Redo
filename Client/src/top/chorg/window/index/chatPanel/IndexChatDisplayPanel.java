package top.chorg.window.index.chatPanel;

import top.chorg.window.foundation.IPanel;

import java.awt.*;


public class IndexChatDisplayPanel extends IPanel {
    public IndexChatDisplayPanel(int width) {
        super(width, 420);
        this.setBackground(Color.WHITE);
    }

    public void resetWidth(int width) {
        this.setPreferredSize(new Dimension(width, 420));
    }

}
