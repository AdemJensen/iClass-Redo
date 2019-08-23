package top.chorg.window.announce;

import top.chorg.kernel.api.announce.AnnouncementListInfo;
import top.chorg.window.foundation.IClickableAdapter;
import top.chorg.window.foundation.IPanel;
import top.chorg.window.foundation.button.ILinkedButton;
import top.chorg.window.foundation.notice.IConfirmNoticeFrame;
import top.chorg.window.foundation.notice.IInformationFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static top.chorg.kernel.Variable.*;

public class AnnounceListLabel extends IPanel {

    public AnnounceListFrame list;
    public ILinkedButton edit, delete;
    public JLabel title, info;

    public AnnounceListLabel(int width, AnnounceListFrame list, AnnouncementListInfo info) {
        super(width, 50, null, new FlowLayout(FlowLayout.LEFT));
        this.list = list;
        this.title = new JLabel(info.title);
        this.title.setPreferredSize(new Dimension(width - 100, 20));
        this.title.setFont(new Font("宋体", Font.PLAIN, 19));

        this.info = new JLabel(info.getEditInfoStr());
        this.info.setPreferredSize(new Dimension(width - 10, 18));
        this.info.setFont(new Font("宋体", Font.PLAIN, 10));
        this.info.setForeground(Color.GRAY);

        this.addComp(this.title);
        if (authNet.getLevelInClass(info.classId, self.id)[0] > 1) {
            this.edit = new ILinkedButton("编辑");
            this.edit.setPreferredSize(new Dimension(35, 20));
            this.edit.addActionListener(e -> {
                try {
                    new EditAnnounceFrame(info.id, list).showWindow();
                } catch (Exception e1) {
                    new IInformationFrame("错误", "数据加载失败！").showWindow();
                }
            });
            this.delete = new ILinkedButton("删除");
            this.delete.setPreferredSize(new Dimension(35, 20));
            this.delete.addActionListener(e -> new IConfirmNoticeFrame(
                    "删除公告《" + info.title + "》",
                    e1 -> {
                        String result = announceNet.removeAnnounce(info.id);
                        checkResult(
                                announceNet.removeAnnounce(info.id),
                                "已成功删除公告《" + info.title + "》"
                        );
                        list.reload();
                    }
            ).showWindow());
            this.addComp(this.edit, this.delete);
        }
        this.addComp(this.info);

        this.addMouseListener(new IClickableAdapter(this));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    new AnnounceInfoFrame(announceNet.getAnnounceInfo(info.id)).showWindow();
                } catch (Exception e1) {
                    new IInformationFrame("错误", "数据加载失败！").showWindow();
                }
            }
        });

    }
}
