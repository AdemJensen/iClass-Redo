package top.chorg.window.announce;

import top.chorg.window.foundation.*;
import top.chorg.window.foundation.form.IFormButtonPanel;
import top.chorg.window.foundation.form.ITextAreaPanel;
import top.chorg.window.foundation.notice.IConfirmNoticeFrame;

import java.awt.*;

public class AddAnnounceFrame extends IFrame {

    public IEditorExtensionToolBar extensionToolBar;
    public ITextAreaPanel titlePanel;
    public IPanel editorArea, toolPanel;
    public ITextEditor editor;
    public IFormButtonPanel buttonPanel;

    public AddAnnounceFrame() {
        super(480, 510, "新建公告", null, DISPOSE_ON_CLOSE);
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);
        this.setLayout(layout);

        this.setLocationCenter(480, 510);

    }

    @Override
    public void addComponents() {
        this.titlePanel = new ITextAreaPanel(460, 35, null,
                30, 415, 20, "标题", "");

        this.editorArea = new IPanel(460, 380);
        FlowLayout layout2 = new FlowLayout(FlowLayout.CENTER);
        layout2.setVgap(0);
        layout2.setHgap(0);
        editorArea.setLayout(layout2);

        this.editor = new ITextEditor(460, 340, null);
        this.extensionToolBar = new IEditorExtensionToolBar(460, editor);

        editorArea.addComp(extensionToolBar, editor);

        this.buttonPanel = new IFormButtonPanel(460, 35, null, "发布", "取消");
        this.buttonPanel.addActionListeners(
                e -> new IConfirmNoticeFrame(
                        "发布公告",
                        f -> submitAction()
                ).showWindow(),
                e -> {      // 退出前二次确认，确保用户手滑不会造成资料丢失
                    if (isWritten()) {
                        new IConfirmNoticeFrame(
                                "放弃已填写的内容",
                                f -> this.dispose()
                        ).showWindow();
                    } else {
                        this.dispose();
                    }
                }
        );

        this.addComp(titlePanel, editorArea, buttonPanel);
    }

    public void submitAction() {
        // TODO: 添加公告请求
        this.dispose();
    }

    private boolean isWritten() {
        return !titlePanel.val().equals("") ||
                !editor.getTextPane().getCompiledText(editor.getTextPane().getUploadImageHash()).equals("[]");
    }

}
