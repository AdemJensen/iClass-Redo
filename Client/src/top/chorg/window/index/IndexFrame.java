package top.chorg.window.index;

import top.chorg.kernel.api.UserInfo;
import top.chorg.window.foundation.*;

import javax.swing.*;
import java.awt.*;

public class IndexFrame extends IFrame {

    IndexUpperPanel upperPanel;
    IPanel masterPanel, userListPanel;

    public IndexFrame(UserInfo selfInfo) {
        super(
                900, 600,
                "iClass",
                new FlowLayout(FlowLayout.CENTER),
                JFrame.DISPOSE_ON_CLOSE
        );
        this.setLocation(250, 130);

        this.upperPanel = new IndexUpperPanel(selfInfo);

        this.masterPanel = new IPanel(
                890, 503,
                null,
                new BorderLayout()
        );

        this.userListPanel = new IPanel(
                300, 50,
                null
        );
        userListPanel.setBackground(new Color(100, 200, 40));
        masterPanel.add(userListPanel, BorderLayout.WEST);

        this.addComp(upperPanel, masterPanel);

    }



}
