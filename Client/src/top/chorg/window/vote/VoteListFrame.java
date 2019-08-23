package top.chorg.window.vote;

import top.chorg.kernel.api.vote.VoteListInfo;
import top.chorg.kernel.api.vote.VoteListQueryInfo;
import top.chorg.window.foundation.IPagedListFrame;
import top.chorg.window.foundation.notice.IInformationFrame;

import javax.swing.*;
import java.awt.*;

import static top.chorg.kernel.Variable.*;

public class VoteListFrame extends IPagedListFrame {

    boolean isManager;
    public int classId;
    public JButton addVote;

    public VoteListFrame(int classId) {
        super(520, 630, "投票列表");
        this.classId = classId;
        addVote = new JButton("添加新投票");
        addVote.setPreferredSize(new Dimension(510, 40));
        addVote.addActionListener(e -> {
            try {
                new AddVoteFrame().showWindow();
            } catch (Exception e1) {
                new IInformationFrame("错误", "因为某些错误，无法显示窗口！").showWindow();
            }
        });

        setTotalPage(99999999);
        setPageNum(1);
    }

    public void reload() {
        this.setPageNum(getPageNum());
    }

    @Override
    public void clearList() {
        super.clearList();
        if (isManager) this.getMasterPanel().add(addVote);
    }

    @Override
    public void setPageNum(int page) {
        super.setPageNum(page);
        boolean newIsManager = authNet.getLevelInClass(classId, self.id)[0] > 1;
        if (isManager != newIsManager) {
            isManager = newIsManager;
            if (isManager) prepareManageView();
            else prepareNormalView();
        }
        VoteListQueryInfo newInfo = voteNet.getVoteList(classId, page);
        this.setTotalPage(newInfo.totalPage);
        this.clearList();
        for (VoteListInfo listInfo : newInfo.infos) {
            this.addItem(new VoteListLabel(510,this, listInfo));
        }
    }

    public void prepareNormalView() {
        this.setSize(new Dimension(520, 630));
        this.getMasterPanel().setPreferredSize(new Dimension(520, 570));
        Component[] stash = getMasterPanel().getComponents();
        clearList();
        getMasterPanel().addComp(stash);
    }

    public void prepareManageView() {
        this.setSize(new Dimension(520, 670));
        this.getMasterPanel().setPreferredSize(new Dimension(520, 610));
        Component[] stash = getMasterPanel().getComponents();
        clearList();
        getMasterPanel().addComp(stash);
    }
}
