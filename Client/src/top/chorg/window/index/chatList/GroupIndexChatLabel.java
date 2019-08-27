package top.chorg.window.index.chatList;

import top.chorg.kernel.api.auth.GroupInfo;
import top.chorg.kernel.api.chat.ChatMsg;
import top.chorg.kernel.api.chat.UnreadChatQueryInfo;
import top.chorg.window.index.chatPanel.ChatBubble;

import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static top.chorg.kernel.Variable.*;

public class GroupIndexChatLabel extends IndexChatLabel {

    public GroupInfo groupInfo;
    public String nameDisplay;

    public GroupIndexChatLabel(int width, int height, int fromId, String nameDisplay) {
        this(
                width, height,
                new UnreadChatQueryInfo(0, fromId, 0, null, ""),
                nameDisplay
        );
    }

    public GroupIndexChatLabel(int width, int height, UnreadChatQueryInfo info, String nameDisplay) {
        super(
                width, height, 0, info.fromId,
                nameDisplay, info.lastDate, info.displayContent, info.amount
        );
        this.nameDisplay = nameDisplay;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                clickAction();
            }
        });
    }

    @Override
    public void clickAction() {
        if (masterFrame.curLabel == GroupIndexChatLabel.this) return;
        if (masterFrame.curViewType != -1) {
            masterFrame.curLabel.storedInputArea = masterFrame.chatPanel.inputPanel.editor.getTextPane()
                    .getCompiledText(
                            masterFrame.chatPanel.inputPanel.editor.getTextPane().getUploadImageHash()
                    );
            masterFrame.chatPanel.inputPanel.editor.getTextPane().setCompiledText(storedInputArea);
            masterFrame.curLabel.setBorder(null);
        }
        this.setBorder(new LineBorder(Color.BLACK, 1));
        masterFrame.curLabel = this;
        this.setBackground(new Color(0xf6f6f6));
        groupInfo = authNet.getGroupInfo(targetId);
        masterFrame.prepareGroupView(groupInfo.name, targetId);
        masterFrame.sidePanel.announcePanel.setGroupId(targetId);
        masterFrame.sidePanel.announcePanel.setAnnounce(announceNet.getLastAnnounce(targetId));
        masterFrame.refreshSideList(targetId);
        ChatMsg[] msg = chatNet.getChatHistory(0, targetId);
        for (ChatMsg chatMsg : msg) {
            int direction;
            if (chatMsg.fromId == self.id) {
                direction = ChatBubble.BUBBLE_RIGHT;
            } else {
                direction = ChatBubble.BUBBLE_LEFT;
            }
            masterFrame.chatPanel.insertChatBubble(
                    direction, getAvatar(0, targetId),
                    nameDisplay, chatMsg.content, chatMsg.time
            );
        }
        masterFrame.chatPanel.refreshBubbleAreaHeight();
        masterFrame.revalidate();
    }
}
