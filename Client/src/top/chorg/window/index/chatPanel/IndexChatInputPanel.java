package top.chorg.window.index.chatPanel;

import top.chorg.window.foundation.IEditorExtensionToolBar;
import top.chorg.window.foundation.IImageIcon;
import top.chorg.window.foundation.IPanel;
import top.chorg.window.foundation.ITextEditor;
import top.chorg.window.foundation.button.IImageButton;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import static top.chorg.kernel.Variable.resource;

public class IndexChatInputPanel extends IPanel {

    IEditorExtensionToolBar extensionToolBar;
    IImageButton sendButton;
    ITextEditor editor;

    public void resetWidth(int width) {
        this.setPreferredSize(new Dimension(width, 420));
        extensionToolBar.resetWidth(width - 40);
        editor.setPreferredSize(new Dimension(width, 104));
    }

    public IndexChatInputPanel(int width) {
        super(width, 140);
        this.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(0xF5F5F5)));
        this.setBackground(Color.WHITE);
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(0);
        layout.setHgap(0);
        this.setLayout(layout);

        editor = new ITextEditor(width, 104, null);
        extensionToolBar = new IEditorExtensionToolBar(width - 40, editor) {
            @Override
            public void fileAction() {
                // TODO
            }
        };

        sendButton = new IImageButton(25, 25, resource("sendIcon.png"));
        sendButton.addActionListener(e -> {
            System.out.println(Arrays.toString(editor.getTextPane().getUploadImageHash()));
            System.out.println(editor.getTextPane().getCompiledText(editor.getTextPane().getUploadImageHash()));
            // TODO: send
            editor.getTextPane().setText("");
            editor.revalidate();
            editor.repaint();
        });

        this.addComp(extensionToolBar, sendButton, editor);

    }
}
