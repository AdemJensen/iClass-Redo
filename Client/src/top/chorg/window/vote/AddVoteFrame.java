package top.chorg.window.vote;

import top.chorg.window.foundation.IEditorExtensionToolBar;
import top.chorg.window.foundation.IFrame;
import top.chorg.window.foundation.IPanel;
import top.chorg.window.foundation.ITextEditor;
import top.chorg.window.foundation.form.IFormButtonPanel;
import top.chorg.window.foundation.form.ITextAreaPanel;
import top.chorg.window.foundation.notice.IConfirmNoticeFrame;
import top.chorg.window.foundation.notice.IInformationFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddVoteFrame extends IFrame {

    public JScrollPane scrollPane;
    public IEditorExtensionToolBar extensionToolBar;
    public ITextAreaPanel titlePanel;
    public IPanel masterPanel, choicePanel, editorArea, toolPanel;
    public ITextEditor editor;
    public IFormButtonPanel buttonPanel;

    public ArrayList<ITextAreaPanel> choice;
    public JButton addChoiceButton;
    public int choicePaneHeight;

    public AddVoteFrame() {
        super(480, 530, "新建投票", new FlowLayout(FlowLayout.CENTER), DISPOSE_ON_CLOSE);
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);
        this.setLayout(layout);

        this.setLocationCenter(480, 530);
    }

    @Override
    public void showWindow() {
        super.showWindow();
        EventQueue.invokeLater(() -> this.scrollPane.getVerticalScrollBar().setValue(0));
    }

    @Override
    public void addComponents() {
        choicePaneHeight = 55;
        this.masterPanel = new IPanel(460, 35 + 240 + choicePaneHeight + 40);
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);
        this.masterPanel.setLayout(layout);
        this.scrollPane = new JScrollPane(masterPanel);
        this.scrollPane.setPreferredSize(new Dimension(480, 440));
        this.scrollPane.setBorder(null);
        this.scrollPane.setOpaque(false);

        this.titlePanel = new ITextAreaPanel(440, 35, null,
                30, 390, 20, "标题", "");

        this.editorArea = new IPanel(440, 200);
        FlowLayout layout2 = new FlowLayout(FlowLayout.CENTER);
        layout2.setVgap(0);
        layout2.setHgap(0);
        editorArea.setLayout(layout2);

        this.editor = new ITextEditor(440, 160, null);
        this.extensionToolBar = new IEditorExtensionToolBar(440, editor);

        editorArea.addComp(extensionToolBar, editor);

        this.choicePanel = new IPanel(440, choicePaneHeight);
        FlowLayout layout3 = new FlowLayout(FlowLayout.CENTER);
        layout3.setVgap(0);
        layout3.setHgap(0);
        choicePanel.setLayout(layout3);
        this.choice = new ArrayList<>();
        this.choice.add(new ITextAreaPanel(
                420, 30, null,
                45, 350, 20, "选项1", ""
        ));
        this.choicePanel.add(choice.get(0), 0);
        this.addChoiceButton = new JButton("添加选项");
        this.addChoiceButton.addActionListener(e -> expandChoicePanel());
        this.choicePanel.add(addChoiceButton, -1);

        this.masterPanel.addComp(titlePanel, editorArea, choicePanel);

        this.buttonPanel = new IFormButtonPanel(460, 35, null, "发布", "取消");
        this.buttonPanel.addActionListeners(
                e -> new IConfirmNoticeFrame(
                        "发布投票",
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

        this.addComp(scrollPane, buttonPanel);
    }

    public void expandChoicePanel() {
        ITextAreaPanel temp = new ITextAreaPanel(
                420, 30, null,
                45, 350, 20,
                "选项" + (this.choice.size() + 1), ""
        );
        this.choice.add(temp);
        this.choicePanel.remove(addChoiceButton);
        this.choicePanel.add(temp);
        this.choicePanel.add(addChoiceButton);
        choicePaneHeight += 30;
        this.masterPanel.setPreferredSize(new Dimension(460, 35 + 200 + choicePaneHeight + 40));
        this.choicePanel.setPreferredSize(new Dimension(440, choicePaneHeight));
        this.revalidate();
    }

    public boolean isChoiceAreaValid() {
        for (ITextAreaPanel iTextAreaPanel : choice) {
            if (iTextAreaPanel.val().length() > 0) return true;
        }
        return false;
    }

    public void submitAction() {
        if (!isChoiceAreaValid()) {
            new IInformationFrame("错误", "选项区域不能为空！").showWindow();
            return;
        }
        // TODO: 添加投票请求
        this.dispose();
    }

    protected boolean isWritten() {
        return !titlePanel.val().equals("") ||
                !editor.getTextPane().getCompiledText(editor.getTextPane().getUploadImageHash()).equals("[]") ||
                !(choice.size() == 1 && choice.get(0).val().equals(""));
    }

}
