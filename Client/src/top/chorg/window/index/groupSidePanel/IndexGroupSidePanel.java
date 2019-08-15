package top.chorg.window.index.groupSidePanel;

import top.chorg.window.foundation.IPanel;

import java.awt.*;

public class IndexGroupSidePanel extends IPanel {

    public IndexGroupSideAnnouncePanel announcePanel;
    public IndexGroupSideList groupSideList;

    public IndexGroupSidePanel() {
        super(260, 585, null, new FlowLayout(FlowLayout.CENTER));
        announcePanel = new IndexGroupSideAnnouncePanel();
        groupSideList = new IndexGroupSideList();
        this.addComp(announcePanel, groupSideList);
    }
}
