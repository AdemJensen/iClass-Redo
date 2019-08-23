package top.chorg.window.vote;

import top.chorg.kernel.api.announce.AnnouncementInfo;
import top.chorg.kernel.api.vote.VoteInfo;
import top.chorg.window.foundation.notice.IInformationFrame;

import java.awt.*;

import static top.chorg.kernel.Variable.announceNet;
import static top.chorg.kernel.Variable.voteNet;

public class EditVoteFrame extends AddVoteFrame {

    VoteListFrame list;
    VoteInfo info;

    /**
     * 通过公告id获取到公告内容
     * @param id 公告id
     */
    public EditVoteFrame(int id, VoteListFrame list) {
        super();
        this.list = list;
        info = voteNet.getVoteInfo(id);
        this.titlePanel.val(info.title);
        this.editor.getTextPane().setCompiledText(info.compiledContent);
        choice.get(0).val(info.choice[0]);
        for (int i = 1; i < info.choice.length; i++) {
            this.expandChoicePanel();
            choice.get(i).val(info.choice[i]);
        }
    }

    @Override
    public void submitAction() {
        if (!isChoiceAreaValid()) {
            new IInformationFrame("错误", "选项区域不能为空！").showWindow();
            return;
        }
        // TODO 提交公告修改申请
        this.dispose();
        EventQueue.invokeLater(() -> list.reload());
    }

    @Override
    protected boolean isWritten() {
        if (choice.size() != info.choice.length) return true;
        for (int i = 0; i < info.choice.length; i++) {
            if (!choice.get(i).val().equals(info.choice[i])) return true;
        }
        return !titlePanel.val().equals(info.title) ||
                !editor.getTextPane().getCompiledText(
                        editor.getTextPane().getUploadImageHash()
                ).equals(info.compiledContent);
    }

}
