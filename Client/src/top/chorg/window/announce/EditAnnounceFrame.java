package top.chorg.window.announce;

import top.chorg.kernel.api.announce.AnnouncementInfo;

import java.awt.*;

import static top.chorg.kernel.Variable.announceNet;

public class EditAnnounceFrame extends AddAnnounceFrame {

    AnnounceListFrame list;
    AnnouncementInfo info;

    /**
     * 通过公告id获取到公告内容
     * @param id 公告id
     */
    public EditAnnounceFrame(int id, AnnounceListFrame list) {
        super();
        this.setTitle("编辑公告《" + info.title + "》");
        this.list = list;
        info = announceNet.getAnnounceInfo(id);
        this.titlePanel.val(info.title);
        this.editor.getTextPane().setCompiledText(info.compiledContent);
    }

    @Override
    public void submitAction() {
        // TODO 提交公告修改申请
        this.dispose();
        EventQueue.invokeLater(() -> list.reload());
    }

    @Override
    protected boolean isWritten() {
        return !titlePanel.val().equals(info.title) ||
                !editor.getTextPane().getCompiledText(
                        editor.getTextPane().getUploadImageHash()
                ).equals(info.compiledContent);
    }

}
