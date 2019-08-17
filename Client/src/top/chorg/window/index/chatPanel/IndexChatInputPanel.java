package top.chorg.window.index.chatPanel;

import top.chorg.window.foundation.IPanel;
import top.chorg.window.foundation.ITextEditor;
import top.chorg.window.foundation.button.IImageButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static top.chorg.kernel.Variable.resource;

public class IndexChatInputPanel extends IPanel {

    IImageButton fontButton, imageButton, whiteBoardButton, fileButton;
    IPanel toolPanel;
    ITextEditor editor;

    public IndexChatInputPanel() {
        super(570, 140);
        this.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(0xF5F5F5)));
        this.setBackground(Color.GREEN);
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(0);
        layout.setHgap(0);
        this.setLayout(layout);

        toolPanel = new IPanel(570, 35, null, new FlowLayout(FlowLayout.LEFT));
        toolPanel.setBackground(Color.WHITE);

        fontButton = new IImageButton(25, 25, resource("fontIcon.png"));
        fontButton.addActionListener(e -> {
            editor.setToolPanelVisibility(!editor.getToolPanelVisibility());
            editor.revalidate();
        });

        imageButton = new IImageButton(25, 25, resource("imageIcon.png"));

        whiteBoardButton = new IImageButton(25, 25, resource("whiteBoardIcon.png"));

        fileButton = new IImageButton(25, 25, resource("fileIcon.png"));

        toolPanel.addComp(fontButton, imageButton, whiteBoardButton, fileButton);

        editor = new ITextEditor(570, 104, null);
        editor.getTextPane().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getExtendedKeyCode() == 0x0) {
                    System.out.println(editor.getTextPane().getCompiledText());
                    // TODO: send
                    editor.getTextPane().setText("");
                    editor.revalidate();
                    editor.repaint();
                }
            }
        });

        this.addComp(toolPanel, editor);

    }
}
