package top.chorg.window.announce;

import top.chorg.kernel.api.announce.AnnouncementInfo;
import top.chorg.window.foundation.IFrame;
import top.chorg.window.foundation.ITextPane;

import javax.swing.*;
import java.awt.*;

public class AnnounceInfoFrame extends IFrame {

    public JLabel titleLabel, infoLabel;
    public JScrollPane scrollPane;
    public ITextPane contentPane;

    public AnnounceInfoFrame(AnnouncementInfo info) {
        super(480, 1, "查看公告《" + info.title + "》", null, DISPOSE_ON_CLOSE);
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);
        this.setLayout(layout);

        this.titleLabel.setText(info.title);
        this.contentPane.setCompiledText(info.compiledContent);
        this.infoLabel.setText(info.getEditInfoStr());

        int height = Math.min(500, contentPane.getTargetHeight() + 100);
        height = Math.max(height, 350);

        this.setSize(new Dimension(480, height));
        this.scrollPane.setPreferredSize(new Dimension(460, height - 100));

        this.setLocationCenter(480, height);

    }

    @Override
    public void addComponents() {
        this.titleLabel = new JLabel();
        this.titleLabel.setPreferredSize(new Dimension(460, 25));
        this.titleLabel.setFont(new Font("宋体", Font.PLAIN, 24));

        this.contentPane = new ITextPane(440, 1);
        this.contentPane.setOpaque(false);
        this.contentPane.setEditable(false);

        this.scrollPane = new JScrollPane(contentPane);
        this.scrollPane.setPreferredSize(new Dimension(460, 250));
        this.scrollPane.setBorder(null);
        this.scrollPane.setOpaque(false);

        this.infoLabel = new JLabel();
        this.infoLabel.setPreferredSize(new Dimension(470, 18));
        this.infoLabel.setFont(new Font("宋体", Font.PLAIN, 10));
        this.infoLabel.setForeground(Color.GRAY);

        this.addComp(titleLabel, scrollPane, infoLabel);
    }
}
