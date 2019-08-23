package top.chorg.window.vote;

import top.chorg.kernel.api.vote.VoteInfo;
import top.chorg.kernel.api.vote.VoteStatisticInfo;
import top.chorg.window.foundation.IFrame;
import top.chorg.window.foundation.IPanel;
import top.chorg.window.foundation.ITextPane;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static top.chorg.kernel.Variable.authNet;
import static top.chorg.kernel.Variable.voteNet;

public class VoteStatisticFrame extends IFrame {

    public JLabel titleLabel, voteTypeLabel, timeLabel, voterAmountLabel;
    public ITextPane statisticPane;
    public JScrollPane scrollPane;
    public IPanel masterPanel;

    public VoteStatisticFrame(int id) {
        super(480, 370, "查看数据", new FlowLayout(FlowLayout.CENTER), DISPOSE_ON_CLOSE);
        VoteInfo voteInfo = voteNet.getVoteInfo(id);
        VoteStatisticInfo statisticInfo = voteNet.getVoteStatisticInfo(id);
        this.titleLabel.setText(voteInfo.title);
        this.voteTypeLabel.setText("投票类型：" + (voteInfo.isMultiple ? "多选" : "单选"));
        this.timeLabel.setText(voteInfo.getTimeInfoStr());
        this.voterAmountLabel.setText("参与投票人数：" + statisticInfo.users.length);

        int[] choiceCount = new int[voteInfo.choice.length];
        String[] names = authNet.getRealName(statisticInfo.users);
        ArrayList<ArrayList<String>> users = new ArrayList<>();
        for (int i = 0; i < voteInfo.choice.length; i++) {
            users.add(new ArrayList<>());
        }
        for (int i = 0; i < statisticInfo.users.length; i++) {
            for (int choice : statisticInfo.userChoices[i]) {
                choiceCount[choice]++;
                users.get(choice).add(names[i]);
            }
        }

        StringBuilder statistic = new StringBuilder();
        for (int i = 0; i < choiceCount.length; i++) {
            statistic.append("选项").append(i + 1).append("：\"");
            if (choiceCount[i] > 0) {
                statistic.append(voteInfo.choice[i]).append("\"，共有");
                statistic.append(choiceCount[i]).append("人选择本项。\n选择此项的用户：");
                statistic.append(users.get(i).get(0));
                for (int j = 1; j < users.get(i).size(); j++) {
                    statistic.append("、").append(users.get(i).get(j));
                }
                statistic.append("。\n\n");
            } else {
                statistic.append(voteInfo.choice[i]).append("\"，没有人选择本项。\n\n");
            }
        }

        this.statisticPane.setText(statistic.toString());
        this.statisticPane.trimWidth(450, true);

        this.masterPanel.setPreferredSize(new Dimension(450, 100 + statisticPane.getTargetHeight()));

    }

    @Override
    public void showWindow() {
        super.showWindow();
        EventQueue.invokeLater(() -> this.scrollPane.getVerticalScrollBar().setValue(0));
    }

    @Override
    public void addComponents() {
        this.masterPanel = new IPanel(450, 100, null, new FlowLayout(FlowLayout.CENTER));
        this.scrollPane = new JScrollPane(masterPanel);
        this.scrollPane.setPreferredSize(new Dimension(470, 340));
        this.scrollPane.setBorder(null);
        this.scrollPane.setOpaque(false);

        this.titleLabel = new JLabel();
        this.titleLabel.setPreferredSize(new Dimension(450, 25));
        this.titleLabel.setFont(new Font("宋体", Font.PLAIN, 24));

        this.voteTypeLabel = new JLabel();
        this.voteTypeLabel.setPreferredSize(new Dimension(450, 15));

        this.timeLabel = new JLabel();
        this.timeLabel.setPreferredSize(new Dimension(450, 15));

        this.voterAmountLabel = new JLabel();
        this.voterAmountLabel.setPreferredSize(new Dimension(450, 15));

        this.statisticPane = new ITextPane(450, 1);
        this.statisticPane.setEditable(false);

        this.masterPanel.addComp(titleLabel, voteTypeLabel, timeLabel, voterAmountLabel, statisticPane);
        this.addComp(scrollPane);
        this.setLocationCenter(480, 370);
    }
}
