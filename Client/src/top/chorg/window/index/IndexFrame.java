package top.chorg.window.index;

import top.chorg.kernel.api.UserInfo;
import top.chorg.window.foundation.*;
import top.chorg.window.index.chatList.IndexChatList;
import top.chorg.window.index.chatPanel.IndexChatPanel;
import top.chorg.window.index.groupSidePanel.IndexGroupSidePanel;

import javax.swing.*;
import java.awt.*;

public class IndexFrame extends IFrame {

    public IndexUpperPanel upperPanel;
    public IPanel masterPanel;
    public IndexChatList chatList;
    public IndexChatPanel chatPanel;
    public IndexGroupSidePanel sidePanel;
    public boolean switcher = false;

    public IndexFrame(UserInfo selfInfo) {
        super(
                1100, 680,
                "iClass",
                JFrame.DISPOSE_ON_CLOSE
        );
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(0);
        layout.setHgap(0);
        this.setLayout(layout);
        this.setLocation(150, 100);
        this.setResizable(false);

        this.upperPanel = new IndexUpperPanel(selfInfo);

        this.masterPanel = new IPanel(
                1100, 600,
                null,
                new BorderLayout()
        );

        this.chatList = new IndexChatList();
        this.chatPanel = new IndexChatPanel();
        this.sidePanel = new IndexGroupSidePanel();

        masterPanel.add(chatList, BorderLayout.WEST);
        masterPanel.add(chatPanel, BorderLayout.CENTER);
        masterPanel.add(sidePanel, BorderLayout.EAST);

        //Test code
//        IPanel panel = new IPanel(200, 200);
//        panel.setBackground(new Color(100, 200, 40));
//        masterPanel.add(panel, BorderLayout.CENTER);
//        IPanel panel2 = new IPanel(200, 200);
//        panel.setBackground(new Color(100, 200, 40));
//        masterPanel.add(panel2, BorderLayout.EAST);
//        JButton button = new JButton("TEST");
//        button.addActionListener(e -> {
//            if (!switcher) {
//                masterPanel.remove(panel2);
//                switcher = true;
//            } else {
//                masterPanel.add(panel2, BorderLayout.EAST);
//                switcher = false;
//            }
//            masterPanel.revalidate();
//        });
//        masterPanel.add(button, BorderLayout.NORTH);
//        //masterPanel.remove(panel2);

        this.addComp(upperPanel, masterPanel);

    }

    public void prepareGroupChatLayout() {

    }

    public void prepareSingleChatLayout() {

    }

}
