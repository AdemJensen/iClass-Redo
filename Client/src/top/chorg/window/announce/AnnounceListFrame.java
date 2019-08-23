package top.chorg.window.announce;

import top.chorg.kernel.api.announce.AnnounceListQueryInfo;
import top.chorg.kernel.api.announce.AnnouncementListInfo;
import top.chorg.window.foundation.IPagedListFrame;
import top.chorg.window.foundation.notice.IInformationFrame;

import javax.swing.*;
import java.awt.*;

import static top.chorg.kernel.Variable.*;

public class AnnounceListFrame extends IPagedListFrame {

    boolean isManager;
    public int classId;
    public JButton addAnnounce;

    public AnnounceListFrame(int classId) {
        super(520, 640, "公告列表");
        this.classId = classId;
        addAnnounce = new JButton("添加新公告");
        addAnnounce.setPreferredSize(new Dimension(510, 40));
        addAnnounce.addActionListener(e -> {
            try {
                new AddAnnounceFrame().showWindow();
            } catch (Exception e1) {
                new IInformationFrame("错误", "因为某些原因，无法显示窗口！").showWindow();
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
        if (isManager) this.getMasterPanel().add(addAnnounce);
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
        AnnounceListQueryInfo newInfo = announceNet.getAnnounceList(classId, page);
        this.setTotalPage(newInfo.totalPage);
        this.clearList();
        for (AnnouncementListInfo listInfo : newInfo.infos) {
            this.addItem(new AnnounceListLabel(510,this, listInfo));
        }
    }

    public void prepareNormalView() {
        this.setSize(new Dimension(520, 640));
        this.getMasterPanel().setPreferredSize(new Dimension(520, 580));
        Component[] stash = getMasterPanel().getComponents();
        clearList();
        getMasterPanel().addComp(stash);
    }

    public void prepareManageView() {
        this.setSize(new Dimension(520, 680));
        this.getMasterPanel().setPreferredSize(new Dimension(520, 620));
        Component[] stash = getMasterPanel().getComponents();
        clearList();
        getMasterPanel().addComp(stash);
    }
}
