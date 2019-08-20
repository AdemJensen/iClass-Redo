package top.chorg.window.index;

import top.chorg.kernel.api.UserInfo;
import top.chorg.window.foundation.*;
import top.chorg.window.index.chatList.IndexChatList;
import top.chorg.window.index.chatPanel.IndexChatPanel;
import top.chorg.window.index.groupSidePanel.IndexGroupSidePanel;

import javax.swing.*;
import java.awt.*;

public class MasterFrame extends IFrame {

    public IndexUpperPanel upperPanel;
    public IPanel masterPanel;
    public IndexChatList chatList;
    public IndexChatPanel chatPanel;
    public IndexGroupSidePanel sidePanel;
    public boolean switcher = false;

    public MasterFrame(UserInfo selfInfo) {
        super(
                1100, 680,
                "iClass",
                JFrame.DISPOSE_ON_CLOSE
        );
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(0);
        layout.setHgap(0);
        this.setLayout(layout);
        this.setLocationCenter(1100, 680);
        this.setResizable(false);

        this.upperPanel = new IndexUpperPanel(selfInfo);

        this.masterPanel = new IPanel(
                1100, 600,
                null,
                new BorderLayout()
        );
        masterPanel.setBackground(new Color(0xf6f6f6));

        this.chatList = new IndexChatList();
        this.chatPanel = new IndexChatPanel(570);
        this.sidePanel = new IndexGroupSidePanel();

        masterPanel.add(chatList, BorderLayout.WEST);
        masterPanel.add(chatPanel, BorderLayout.CENTER);
        masterPanel.add(sidePanel, BorderLayout.EAST);

        chatPanel.voteButton.addActionListener(e -> {
            if (!switcher) {
                prepareSingleChat();
                switcher = true;
            } else {
                prepareGroupChat();
                switcher = false;
            }

        });

        this.addComp(upperPanel, masterPanel);

    }

    public void prepareGroupChat() {
        masterPanel.add(sidePanel, BorderLayout.EAST);
        chatPanel.resetWidth(570);
        masterPanel.revalidate();
    }

    public void prepareSingleChat() {
        masterPanel.remove(sidePanel);
        chatPanel.resetWidth(840);
        masterPanel.revalidate();
    }

}
