package top.chorg.window.index;

import top.chorg.kernel.api.auth.GroupInfo;
import top.chorg.kernel.api.auth.UserInfo;
import top.chorg.kernel.api.chat.UnreadChatQueryInfo;
import top.chorg.window.foundation.*;
import top.chorg.window.index.chatList.GroupIndexChatLabel;
import top.chorg.window.index.chatList.IndexChatLabel;
import top.chorg.window.index.chatList.IndexChatList;
import top.chorg.window.index.chatList.UserIndexChatLabel;
import top.chorg.window.index.chatPanel.IndexChatPanel;
import top.chorg.window.index.groupSidePanel.IndexGroupSidePanel;

import javax.swing.*;
import java.awt.*;

import static top.chorg.kernel.Variable.*;

public class MasterFrame extends IFrame {

    public IndexUpperPanel upperPanel;
    public IPanel masterPanel;
    public IndexChatList chatList;
    public IndexChatPanel chatPanel;
    public IndexGroupSidePanel sidePanel;
    public boolean switcher = false;

    public IndexChatLabel curLabel = null;
    public int curViewType = -1;

    public MasterFrame(UserInfo selfInfo) {
        super(
                1100, 680,
                "iClass",
                JFrame.DISPOSE_ON_CLOSE
        );
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(0);
        layout.setHgap(0);
        this.setLayout(layout);
        this.setLocationCenter(1100, 680);

        this.upperPanel = new IndexUpperPanel(selfInfo);

        this.masterPanel = new IPanel(
                1100, 600,
                null,
                new BorderLayout()
        );
        masterPanel.setBackground(new Color(0xf6f6f6));

        this.chatList = new IndexChatList();


        masterPanel.add(chatList, BorderLayout.WEST);

        this.addComp(upperPanel, masterPanel);

    }

    public void refreshChatList() {
        chatList.master.removeAll();
        UnreadChatQueryInfo[] info = chatNet.getUnreadChat();
        for (UnreadChatQueryInfo unreadChatQueryInfo : info) {
            if (unreadChatQueryInfo.msgType == 0) {
                chatList.addItem(
                        new GroupIndexChatLabel(
                                240, 45, unreadChatQueryInfo,
                                authNet.getGroupName(unreadChatQueryInfo.fromId)[0]
                        )
                );
            } else {
                chatList.addItem(
                        new UserIndexChatLabel(
                                240, 45, unreadChatQueryInfo,
                                authNet.getRealName(unreadChatQueryInfo.fromId)[0]
                        )
                );
            }
        }
        this.revalidate();
    }

    public void prepareGroupView(String displayName, int groupId) {
        if (curViewType == -1) {
            this.chatPanel = new IndexChatPanel(570);
            this.sidePanel = new IndexGroupSidePanel();
            masterPanel.add(chatPanel, BorderLayout.CENTER);
            masterPanel.add(sidePanel, BorderLayout.EAST);
            curViewType = 0;
        } else if (curViewType == 1) {
            masterPanel.add(sidePanel, BorderLayout.EAST);
            curViewType = 0;
        }
        chatPanel.prepareGroupView(displayName, groupId);
        chatPanel.resetWidth(570);
        chatPanel.displayPanel.resetDisplayArea();
        masterPanel.revalidate();
    }

    public void prepareSingleView(String displayName, int userId) {
        if (curViewType == -1) {
            this.chatPanel = new IndexChatPanel(840);
            this.sidePanel = new IndexGroupSidePanel();
            masterPanel.add(chatPanel, BorderLayout.CENTER);
            curViewType = 0;
        } else if (curViewType == 0) {
            masterPanel.remove(sidePanel);
            curViewType = 1;
        }
        chatPanel.prepareSingleView(displayName, userId);
        chatPanel.resetWidth(840);
        chatPanel.resetBubbleArea();
        masterPanel.revalidate();
    }

    public void turnToChat(int chatType, int targetId) {
        if (curViewType == chatType && curLabel.targetId == targetId) return;
        if (chatType == 0) {
            boolean ok = false;
            for (Component component : chatList.master.getComponents()) {
                if (component instanceof GroupIndexChatLabel &&
                        ((GroupIndexChatLabel) component).targetId == targetId) {
                    ((GroupIndexChatLabel) component).clickAction();
                    ok = true;
                }
            }
            if (!ok) {
                IndexChatLabel label = new GroupIndexChatLabel(
                        240, 45,
                        targetId, authNet.getGroupName(targetId)[0]
                );
                chatList.addItem(label, 0);
                label.clickAction();
            }
        } else {
            boolean ok = false;
            for (Component component : chatList.master.getComponents()) {
                if (component instanceof UserIndexChatLabel &&
                        ((UserIndexChatLabel) component).targetId == targetId) {
                    ((UserIndexChatLabel) component).clickAction();
                    ok = true;
                }
            }
            if (!ok) {
                IndexChatLabel label = new UserIndexChatLabel(
                        240, 45,
                        targetId, authNet.getRealName(targetId)[0]
                );
                chatList.addItem(label, 0);
                label.clickAction();
            }
        }
        this.revalidate();
    }

    public void bringTopFromList(IndexChatLabel label) {
        this.chatList.remove(label);
        this.chatList.addItem(label, 0);
        this.chatList.revalidate();
    }

    public void refreshSideList(int groupId) {
        if (curViewType != 0) return;
        GroupInfo groupInfo = authNet.getGroupInfo(groupId);
        int[] level = authNet.getLevelInGroup(groupId, groupInfo.classmates);
        String[] name = authNet.getRealName(groupInfo.classmates);
        boolean[] isOnline = authNet.isUserOnline(groupInfo.classmates);
        sidePanel.groupSideList.clearList();
        for (int i = 0; i < level.length; i++) {
            if (level[i] == 3) sidePanel.groupSideList.addItem(
                    groupInfo.classmates[i],
                    new IImageIcon(getAvatar(1, groupInfo.classmates[i])),
                    name[i], level[i], isOnline[i]
            );
        }
        for (int i = 0; i < level.length; i++) {
            if (level[i] == 2 && isOnline[i]) sidePanel.groupSideList.addItem(
                    groupInfo.classmates[i],
                    new IImageIcon(getAvatar(1, groupInfo.classmates[i])),
                    name[i], level[i], isOnline[i]
            );
        }
        for (int i = 0; i < level.length; i++) {
            if (level[i] == 1 && isOnline[i]) sidePanel.groupSideList.addItem(
                    groupInfo.classmates[i],
                    new IImageIcon(getAvatar(1, groupInfo.classmates[i])),
                    name[i], level[i], isOnline[i]
            );
        }
        for (int i = 0; i < level.length; i++) {
            if (level[i] == 2 && !isOnline[i]) sidePanel.groupSideList.addItem(
                    groupInfo.classmates[i],
                    new IImageIcon(getAvatar(1, groupInfo.classmates[i])),
                    name[i], level[i], isOnline[i]
            );
        }
        for (int i = 0; i < level.length; i++) {
            if (level[i] == 1 && !isOnline[i]) sidePanel.groupSideList.addItem(
                    groupInfo.classmates[i],
                    new IImageIcon(getAvatar(1, groupInfo.classmates[i])),
                    name[i], level[i], isOnline[i]
            );
        }
    }

}
