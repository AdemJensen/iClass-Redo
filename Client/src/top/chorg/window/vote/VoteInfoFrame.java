package top.chorg.window.vote;

import top.chorg.kernel.api.vote.MakeVoteInfo;
import top.chorg.kernel.api.vote.VoteInfo;
import top.chorg.window.foundation.IFrame;
import top.chorg.window.foundation.IPanel;
import top.chorg.window.foundation.ITextPane;
import top.chorg.window.foundation.form.ICheckBoxPanel;
import top.chorg.window.foundation.form.IFormButtonPanel;
import top.chorg.window.foundation.form.IRadioBoxPanel;
import top.chorg.window.foundation.notice.IConfirmNoticeFrame;

import javax.swing.*;
import java.awt.*;

import static top.chorg.kernel.Variable.checkResult;
import static top.chorg.kernel.Variable.voteNet;

public class VoteInfoFrame extends IFrame {

    VoteInfo info;

    public JLabel titleLabel, infoLabel, timeLabel;
    public IPanel masterPanel;
    public JScrollPane scrollPane;
    public IRadioBoxPanel singleChoice;
    public ICheckBoxPanel multiChoice;
    public IFormButtonPanel buttonPanel;
    public ITextPane contentPane;

    public VoteInfoFrame(VoteInfo info) {
        super(480, 1, "查看投票《" + info.title + "》", null, DISPOSE_ON_CLOSE);
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);
        this.setLayout(layout);
        this.info = info;

        this.titleLabel.setText(info.title);
        this.contentPane.setCompiledText(info.compiledContent);
        this.infoLabel.setText(info.getEditInfoStr());
        this.timeLabel.setText(info.getTimeInfoStr());

        int choiceAreaH;
        if (info.isMultiple) {
            multiChoice = new ICheckBoxPanel(
                    440, null, 30, "选项", new FlowLayout(FlowLayout.LEFT),
                    info.choice
            );
            masterPanel.addComp(multiChoice);
            choiceAreaH = multiChoice.getTargetHeight();
        } else {
            singleChoice = new IRadioBoxPanel(
                    440, null, 30, "选项", new FlowLayout(FlowLayout.LEFT),
                    info.choice
            );
            masterPanel.addComp(singleChoice);
            choiceAreaH = singleChoice.getTargetHeight();
        }

        this.masterPanel.setPreferredSize(new Dimension(
                440, contentPane.getTargetHeight() + choiceAreaH + 60
        ));

        this.buttonPanel = new IFormButtonPanel(440, 40, null, "提交投票");
        this.buttonPanel.addActionListeners(e -> new IConfirmNoticeFrame("提交您的投票", e1 -> {
            int[] choice;
            if (info.isMultiple) {
                choice = multiChoice.val();
            } else {
                choice = new int[]{singleChoice.val()};
            }
            checkResult(voteNet.makeVote(new MakeVoteInfo(info.id, choice)), "投票成功！");
        }).showWindow());

        masterPanel.addComp(buttonPanel);


        int height = Math.min(630, contentPane.getTargetHeight() + choiceAreaH + 60 + 130);
        height = Math.max(height, 480);

        this.setSize(new Dimension(480, height));
        this.scrollPane.setPreferredSize(new Dimension(460, height - 130));


        this.setLocationCenter(480, height);

    }

    @Override
    public void showWindow() {
        super.showWindow();
        EventQueue.invokeLater(() -> this.scrollPane.getVerticalScrollBar().setValue(0));
    }

    @Override
    public void addComponents() {
        this.titleLabel = new JLabel();
        this.titleLabel.setPreferredSize(new Dimension(460, 25));
        this.titleLabel.setFont(new Font("宋体", Font.PLAIN, 24));

        this.contentPane = new ITextPane(440, 2);
        this.contentPane.setOpaque(false);
        this.contentPane.setEditable(false);

        this.masterPanel = new IPanel(440, 1);
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(5);
        layout.setHgap(0);
        this.masterPanel.setLayout(layout);

        masterPanel.addComp(this.contentPane);

        this.scrollPane = new JScrollPane(masterPanel);
        this.scrollPane.setPreferredSize(new Dimension(460, 250));
        this.scrollPane.setOpaque(false);
        this.scrollPane.setBorder(null);

        this.timeLabel = new JLabel();
        this.timeLabel.setPreferredSize(new Dimension(470, 18));
        this.timeLabel.setFont(new Font("宋体", Font.PLAIN, 10));

        this.infoLabel = new JLabel();
        this.infoLabel.setPreferredSize(new Dimension(470, 18));
        this.infoLabel.setForeground(Color.GRAY);
        this.infoLabel.setFont(new Font("宋体", Font.PLAIN, 10));

        this.addComp(titleLabel, scrollPane, timeLabel, infoLabel);
    }
}
