package top.chorg.window.index.groupSidePanel;

import top.chorg.window.foundation.IPanel;

import java.awt.*;

public class IndexGroupSidePanel extends IPanel {

    public IndexGroupSideAnnouncePanel announcePanel;
    public IndexGroupSideList groupSideList;

    public IndexGroupSidePanel() {
        super(270, 600, null);
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        this.setBackground(new Color(0xf6f6f6));
        layout.setVgap(0);
        layout.setHgap(0);
        this.setLayout(layout);
        announcePanel = new IndexGroupSideAnnouncePanel();
        groupSideList = new IndexGroupSideList();
        this.addComp(announcePanel, groupSideList);
    }
}
