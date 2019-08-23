package top.chorg.window.vote;

import top.chorg.kernel.api.vote.VoteListInfo;
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

public class VoteListLabel extends IPanel {

    public VoteListFrame list;
    public ILinkedButton statistic, edit, delete;
    public JLabel title, editInfo, timeInfo;

    public VoteListLabel(int width, VoteListFrame list, VoteListInfo info) {
        super(width, 70, null, new FlowLayout(FlowLayout.LEFT));
        this.list = list;
        this.title = new JLabel(info.title);
        this.title.setPreferredSize(new Dimension(width - 140, 20));
        this.title.setFont(new Font("宋体", Font.PLAIN, 19));

        this.timeInfo = new JLabel(info.getTimeInfoStr());
        this.timeInfo.setPreferredSize(new Dimension(width - 10, 18));
        this.timeInfo.setFont(new Font("宋体", Font.PLAIN, 10));

        this.editInfo = new JLabel(info.getEditInfoStr());
        this.editInfo.setPreferredSize(new Dimension(width - 10, 18));
        this.editInfo.setFont(new Font("宋体", Font.PLAIN, 10));
        this.editInfo.setForeground(Color.GRAY);

        this.addComp(this.title);
        if (authNet.getLevelInClass(info.classId, self.id)[0] > 1) {
            this.statistic = new ILinkedButton("数据");
            this.statistic.setPreferredSize(new Dimension(35, 20));
            this.statistic.addActionListener(e -> {
                try {
                    new VoteStatisticFrame(info.id).showWindow();
                } catch (Exception e1) {
                    new IInformationFrame("错误", "数据加载失败！").showWindow();
                }
            });
            this.edit = new ILinkedButton("编辑");
            this.edit.setPreferredSize(new Dimension(35, 20));
            this.edit.addActionListener(e -> {
                try {
                    new EditVoteFrame(info.id, list).showWindow();
                } catch (Exception e1) {
                    new IInformationFrame("错误", "数据加载失败！").showWindow();
                }
            });
            this.delete = new ILinkedButton("删除");
            this.delete.setPreferredSize(new Dimension(35, 20));
            this.delete.addActionListener(e -> new IConfirmNoticeFrame(
                    "删除投票《" + info.title + "》",
                    e1 -> {
                        checkResult(
                                voteNet.removeVote(info.id),
                                "已成功删除投票《" + info.title + "》"
                        );
                        list.reload();
                    }
            ).showWindow());
            this.addComp(this.statistic, this.edit, this.delete);
        }
        this.addComp(this.timeInfo, this.editInfo);

        this.addMouseListener(new IClickableAdapter(this));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    new VoteInfoFrame(voteNet.getVoteInfo(info.id)).showWindow();
                } catch (Exception e1) {
                    new IInformationFrame("错误", "数据加载失败！").showWindow();
                }
            }
        });

    }
}
