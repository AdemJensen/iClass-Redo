package top.chorg.window.index.chatPanel;

import top.chorg.window.foundation.IPanel;
import top.chorg.window.foundation.button.IImageButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static top.chorg.kernel.Variable.resource;

public class IndexChatPanel extends IPanel {

    public IPanel infoPanel;
    public JLabel infoLabel;
    public IndexChatDisplayPanel displayPanel;
    public IndexChatInputPanel inputPanel;
    public JButton fileButton, voteButton, settingsButton;

    public IndexChatPanel() {
        super(570, 600, null);
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(0);
        layout.setHgap(0);
        this.setLayout(layout);

        infoPanel = new IPanel(570, 40, null, new FlowLayout(FlowLayout.LEFT));
        infoPanel.setBackground(Color.WHITE);

        infoLabel = new JLabel("TEST CHAT");
        infoLabel.setFont(new Font("宋体", Font.PLAIN, 15));
        infoLabel.setPreferredSize(new Dimension(570 - 115, 40 - 10));
        infoLabel.setBorder(new EmptyBorder(0, 10, 0, 0));

        fileButton = new IImageButton(30, 30, resource("fileIcon.png"));
        voteButton = new IImageButton(30, 30, resource("voteIcon.png"));
        settingsButton = new IImageButton(30, 30, resource("settingsIcon.png"));

        infoPanel.addComp(infoLabel, fileButton, voteButton, settingsButton);

        displayPanel = new IndexChatDisplayPanel();
        inputPanel = new IndexChatInputPanel();

        this.addComp(infoPanel, displayPanel, inputPanel);

    }
}
