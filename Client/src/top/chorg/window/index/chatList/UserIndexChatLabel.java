package top.chorg.window.index.chatList;

import top.chorg.kernel.api.auth.UserInfo;
import top.chorg.kernel.api.chat.ChatMsg;
import top.chorg.kernel.api.chat.UnreadChatQueryInfo;
import top.chorg.window.index.chatPanel.ChatBubble;

import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static top.chorg.kernel.Variable.*;

public class UserIndexChatLabel extends IndexChatLabel {

    public UserInfo userInfo;
    public String nameDisplay;

    public UserIndexChatLabel(int width, int height, int fromId, String nameDisplay) {
        this(
                width, height,
                new UnreadChatQueryInfo(1, fromId, 0, null, ""),
                nameDisplay
        );
    }

    public UserIndexChatLabel(int width, int height, UnreadChatQueryInfo info, String nameDisplay) {
        super(
                width, height, 1, info.fromId,
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
        if (masterFrame.curLabel == UserIndexChatLabel.this) return;
        if (masterFrame.curViewType != -1) {
            masterFrame.curLabel.storedInputArea = masterFrame.chatPanel.inputPanel.editor.getTextPane()
                    .getCompiledText(
                            masterFrame.chatPanel.inputPanel.editor.getTextPane().getUploadImageHash()
                    );
            masterFrame.chatPanel.inputPanel.editor.getTextPane().setCompiledText(storedInputArea);
            masterFrame.curLabel.setBorder(null);
        }
        masterFrame.curLabel = this;
        this.setBorder(new LineBorder(Color.BLACK, 1));
        userInfo = authNet.getUserInfo(targetId);
        masterFrame.prepareSingleView(userInfo.realName, targetId);
        ChatMsg[] msg = chatNet.getChatHistory(1, targetId);
        for (ChatMsg chatMsg : msg) {
            int direction;
            if (chatMsg.fromId == self.id) {
                direction = ChatBubble.BUBBLE_RIGHT;
            } else {
                direction = ChatBubble.BUBBLE_LEFT;
            }
            masterFrame.chatPanel.insertChatBubble(
                    direction, getAvatar(1, targetId),
                    nameDisplay, chatMsg.content, chatMsg.time
            );
        }
        masterFrame.chatPanel.refreshBubbleAreaHeight();
        masterFrame.revalidate();
    }
}
