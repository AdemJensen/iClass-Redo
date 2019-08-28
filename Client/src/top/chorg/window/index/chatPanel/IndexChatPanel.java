package top.chorg.window.index.chatPanel;

import top.chorg.window.file.FileListFrame;
import top.chorg.window.foundation.IImageIcon;
import top.chorg.window.foundation.IPanel;
import top.chorg.window.foundation.button.IImageButton;
import top.chorg.window.vote.VoteListFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Date;

import static top.chorg.kernel.Variable.resource;

public class IndexChatPanel extends IPanel {

    public IPanel infoPanel;
    public JLabel infoLabel;
    public IndexChatDisplayPanel displayPanel;
    public IndexChatInputPanel inputPanel;
    public JButton fileButton, voteButton, settingsButton;

    public int viewType = 0, targetId = -1;
    public int width;
    public Date lastMsgTime = new Date(0);

    public void resetWidth(int width) {
        this.width = width;
        this.setPreferredSize(new Dimension(width, 600));
        infoPanel.setPreferredSize(new Dimension(width, 40));
        if (viewType == 0) {
            infoLabel.setPreferredSize(new Dimension(width - 115, 40 - 10));
        } else {
            infoLabel.setPreferredSize(new Dimension(width - 80, 40 - 10));
        }

        displayPanel.resetWidth(width);
        inputPanel.resetWidth(width);
    }

    public IndexChatPanel(int width) {
        super(width, 600, null);
        this.width = width;
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(0);
        layout.setHgap(0);
        this.setLayout(layout);

        infoPanel = new IPanel(width, 40, null, new FlowLayout(FlowLayout.LEFT));
        infoPanel.setBackground(Color.WHITE);

        infoLabel = new JLabel("TEST CHAT");
        infoLabel.setFont(new Font("宋体", Font.PLAIN, 15));
        infoLabel.setPreferredSize(new Dimension(width - 115, 40 - 10));
        infoLabel.setBorder(new EmptyBorder(0, 10, 0, 0));

        fileButton = new IImageButton(30, 30, resource("fileIcon.png"));
        fileButton.addActionListener(e -> new FileListFrame(viewType, targetId).showWindow());
        voteButton = new IImageButton(30, 30, resource("voteIcon.png"));
        voteButton.addActionListener(e -> new VoteListFrame(targetId).showWindow());
        settingsButton = new IImageButton(30, 30, resource("settingsIcon.png"));

        infoPanel.addComp(infoLabel, fileButton, voteButton, settingsButton);


        displayPanel = new IndexChatDisplayPanel(width);
        inputPanel = new IndexChatInputPanel(width);

        this.addComp(infoPanel, displayPanel, inputPanel);

    }

    public void resetLastTime(Date time) {
        lastMsgTime = time;
    }

    public void resetBubbleArea() {
        this.displayPanel.resetDisplayArea();
    }

    public void insertChatBubble(int direction, IImageIcon avatar, String name, String compiledDoc, Date msgTime) {
        if (msgTime.getTime() - lastMsgTime.getTime() > 60000) {
            lastMsgTime = msgTime;
            this.displayPanel.addTimeLabel(lastMsgTime);
        }
        this.displayPanel.addBubble(direction, avatar, name, compiledDoc);
    }

    public void refreshBubbleAreaHeight() {
        this.displayPanel.resetHeight();
        this.revalidate();
    }

    public void prepareSingleView(String displayName, int userId) {
        if (viewType == 0) {
            this.infoPanel.remove(voteButton);
        }
        viewType = 1;
        targetId = userId;
        this.infoLabel.setText(displayName);
        this.revalidate();
    }

    public void prepareGroupView(String displayName, int groupId) {
        if (viewType == 1) {
            this.infoPanel.remove(settingsButton);
            this.infoPanel.addComp(voteButton, settingsButton);
        }
        viewType = 0;
        targetId = groupId;
        this.infoLabel.setText(displayName);
        this.revalidate();
    }

}
