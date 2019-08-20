package top.chorg.window.index.chatPanel;

import top.chorg.window.foundation.IImageIcon;
import top.chorg.window.foundation.IPanel;
import top.chorg.window.foundation.ITextEditor;
import top.chorg.window.foundation.button.IImageButton;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

import static top.chorg.kernel.Variable.resource;

public class IndexChatInputPanel extends IPanel {

    IImageButton fontButton, imageButton, whiteBoardButton, fileButton, sendButton;
    IPanel toolPanel;
    ITextEditor editor;

    public void resetWidth(int width) {
        this.setPreferredSize(new Dimension(width, 420));
        toolPanel.setPreferredSize(new Dimension(width - 40, 35));
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

        toolPanel = new IPanel(width - 40, 35, null, new FlowLayout(FlowLayout.LEFT));
        toolPanel.setBackground(Color.WHITE);

        fontButton = new IImageButton(25, 25, resource("fontIcon.png"));
        fontButton.addActionListener(e -> {
            editor.setToolPanelVisibility(!editor.getToolPanelVisibility());
            editor.revalidate();
        });

        imageButton = new IImageButton(25, 25, resource("imageIcon.png"));
        imageButton.addActionListener(e -> {
            FileDialog dialog = new FileDialog(new Frame(), "选择图片", FileDialog.LOAD);
            dialog.setFilenameFilter((dir, name) -> {
                String process = name.toUpperCase();
                return process.endsWith("JPG") || process.endsWith("JPEG") ||
                        process.endsWith("PNG") || process.endsWith("BMP");
            });
            dialog.setMultipleMode(true);
            dialog.setVisible(true);
            File[] list = dialog.getFiles();
            int pos = 0;
            try {
                pos = editor.getTextPane().getSelectionStart();
                editor.getTextPane().getStyledDocument().remove(
                        pos,
                        editor.getTextPane().getSelectionEnd() - pos
                );
            } catch (BadLocationException ex) {
                System.out.println("WARNING: Bad location at IndexChatInputPanel.imageButton");
            }
            for (File file : list) {
                editor.getTextPane().insertIcon(
                        pos,
                        new IImageIcon(file.getPath())
                );
                pos++;
            }
            editor.revalidate();
            editor.repaint();
        });


        whiteBoardButton = new IImageButton(25, 25, resource("whiteBoardIcon.png"));

        fileButton = new IImageButton(25, 25, resource("fileIcon.png"));

        sendButton = new IImageButton(25, 25, resource("sendIcon.png"));
        sendButton.addActionListener(e -> {
            System.out.println(Arrays.toString(editor.getTextPane().getUploadImageHash()));
            System.out.println(editor.getTextPane().getCompiledText(editor.getTextPane().getUploadImageHash()));
            // TODO: send
            editor.getTextPane().setText("");
            editor.revalidate();
            editor.repaint();
        });

        toolPanel.addComp(fontButton, imageButton, whiteBoardButton, fileButton);

        editor = new ITextEditor(width, 104, null);
        //editor.getTextPane().setCompiledText("[\"content\",\"{\\\"color\\\":{\\\"value\\\":-16777216,\\\"falpha\\\":0.0},\\\"family\\\":\\\"Lucida Grande\\\",\\\"size\\\":13,\\\"isItalic\\\":false,\\\"isBold\\\":false,\\\"isUnderline\\\":false,\\\"startOff\\\":0,\\\"len\\\":25,\\\"content\\\":\\\"This is an ord\ninaty test\\\\n\\\"}\"]\n");

        this.addComp(toolPanel, sendButton, editor);

    }
}
