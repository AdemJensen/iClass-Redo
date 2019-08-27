package top.chorg.window.index.groupSidePanel;

import top.chorg.kernel.api.announce.AnnouncementInfo;
import top.chorg.window.announce.AnnounceListFrame;
import top.chorg.window.foundation.IPanel;
import top.chorg.window.foundation.ITextPane;
import top.chorg.window.foundation.button.IImageButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static top.chorg.kernel.Variable.resource;

public class IndexGroupSideAnnouncePanel extends IPanel {

    public JLabel announceName;
    public IImageButton expandButton;
    public ITextPane content;
    public JScrollPane scrollPane;

    public int groupId;

    public IndexGroupSideAnnouncePanel() {
        super(270, 200);
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
        layout.setVgap(0);
        layout.setHgap(0);
        this.setLayout(layout);
        this.setBackground(new Color(0xf6f6f6));

        announceName = new JLabel("TEST NAME");
        announceName.setPreferredSize(new Dimension(235, 35));
        announceName.setBorder(new EmptyBorder(5, 8, 5, 8));
        announceName.setBackground(new Color(0xf6f6f6));
        announceName.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                expandAction();
            }
        });

        expandButton = new IImageButton(35, 35, resource("rArrowIcon.png"));
        expandButton.addActionListener(e -> expandAction());
        expandButton.setBackground(new Color(0xf6f6f6));

        content = new ITextPane(240, 175);
        content.setEditable(false);
        content.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                expandAction();
            }
        });
        content.setText("Test area.");
        content.setBackground(new Color(0xf6f6f6));
        content.setBorder(new EmptyBorder(8, 8, 8, 8));
        scrollPane = new JScrollPane(content);
        scrollPane.setPreferredSize(new Dimension(270, 175));
        scrollPane.setBackground(new Color(0xf6f6f6));
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.addComp(announceName, expandButton, scrollPane);
    }

    public void expandAction() {
        new AnnounceListFrame(groupId).showWindow();
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public void setAnnounce(AnnouncementInfo info) {
        this.announceName.setText(info.title);
        this.content.setCompiledText(info.compiledContent);
    }

}
