package top.chorg.window.index.chatPanel;

import top.chorg.window.foundation.IImageIcon;
import top.chorg.window.foundation.IPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ChatMessagePane extends IPanel {

    public IPanel msgPanel, avatarPanel;

    public ChatBubble bubble;
    public JLabel name, avatarLabel;

    public ChatMessagePane(int width, int direction, IImageIcon avatar, String name, String compiledDoc) {
        super(width, 1);
        this.setBackground(Color.WHITE);

        msgPanel = new IPanel(width, 1, null);
        avatarPanel = new IPanel(35, 1, null);
        FlowLayout layout1, layout2;
        layout2 = new FlowLayout(FlowLayout.LEADING);
        if (direction == 0) {
            this.name = new JLabel(name, JLabel.LEFT);
            layout1 = new FlowLayout(FlowLayout.LEFT);
            this.setLayout(new FlowLayout(FlowLayout.LEFT));
        } else {
            this.name = new JLabel(name, JLabel.RIGHT);
            layout1 = new FlowLayout(FlowLayout.RIGHT);
            this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        }
        layout1.setVgap(5);
        layout1.setHgap(0);
        layout2.setVgap(0);
        layout2.setHgap(0);
        msgPanel.setLayout(layout1);
        avatarPanel.setLayout(layout2);
        msgPanel.setBackground(Color.WHITE);
        avatarPanel.setBackground(Color.WHITE);

        avatar.setSize(35, 35);
        this.avatarLabel = new JLabel(avatar);
        avatarLabel.setPreferredSize(new Dimension(35, 35));

        this.name.setFont(new Font("宋体", Font.PLAIN, 12));
        this.name.setForeground(Color.GRAY);
        this.name.setBorder(new EmptyBorder(0, 10, 0, 10));

        this.bubble = new ChatBubble(
                width - 90, direction,
                direction == 0 ? new Color(0xF3F3F3) : new Color(0xDEF3FE),
                compiledDoc
        );

        this.name.setPreferredSize(new Dimension(width - 90, 15));
        this.msgPanel.setPreferredSize(new Dimension(width - 90, bubble.getTargetHeight() + 40));
        this.avatarPanel.setPreferredSize(new Dimension(35, bubble.getTargetHeight() + 25));

        this.setPreferredSize(new Dimension(width, bubble.getTargetHeight() + 40));

        msgPanel.addComp(this.name, this.bubble);
        avatarPanel.addComp(this.avatarLabel);
        if (direction == 0) {
            this.addComp(this.avatarPanel, this.msgPanel);
        } else {
            this.addComp(this.msgPanel, this.avatarPanel);
        }


    }

    public void resetWidth(int width) {
        this.name.setPreferredSize(new Dimension(width - 90, 10));
        this.bubble.resetWidth(width - 90);
        this.msgPanel.setPreferredSize(new Dimension(width - 90, bubble.getTargetHeight() + 35));
        this.avatarPanel.setPreferredSize(new Dimension(35, bubble.getTargetHeight() + 25));
        this.setPreferredSize(new Dimension(width, bubble.getTargetHeight() + 35));
        this.revalidate();
        this.repaint();
    }

}
