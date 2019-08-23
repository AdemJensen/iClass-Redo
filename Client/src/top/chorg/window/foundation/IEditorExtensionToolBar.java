package top.chorg.window.foundation;

import top.chorg.window.foundation.button.IImageButton;
import top.chorg.window.miniDrawPad.MiniDrawPad;

import javax.swing.text.BadLocationException;
import java.awt.*;
import java.io.File;

import static top.chorg.kernel.Variable.resource;

public class IEditorExtensionToolBar extends IPanel {

    IImageButton fontButton, imageButton, drawPadButton, fileButton;
    boolean fontEnabled, imageEnabled, drawPadEnabled, fileEnabled;
    ITextEditor editor;

    public boolean isFontEnabled() {
        return fontEnabled;
    }

    public void setFontEnabled(boolean fontEnabled) {
        this.fontEnabled = fontEnabled;
        this.reconstruct();
    }

    public boolean isImageEnabled() {
        return imageEnabled;
    }

    public void setImageEnabled(boolean imageEnabled) {
        this.imageEnabled = imageEnabled;
        this.reconstruct();
    }

    public boolean isDrawPadEnabled() {
        return drawPadEnabled;
    }

    public void setDrawPadEnabled(boolean drawPadEnabled) {
        this.drawPadEnabled = drawPadEnabled;
        this.reconstruct();
    }

    public boolean isFileEnabled() {
        return fileEnabled;
    }

    public void setFileEnabled(boolean fileEnabled) {
        this.fileEnabled = fileEnabled;
        this.reconstruct();
    }

    public void resetWidth(int width) {
        this.setPreferredSize(new Dimension(width, 35));
    }

    public IEditorExtensionToolBar(int width, ITextEditor editor) {
        super(width, 35, null, new FlowLayout(FlowLayout.LEFT));
        this.setBackground(Color.WHITE);
        this.editor = editor;
        fontEnabled = imageEnabled = drawPadEnabled = true;

        fontButton = new IImageButton(25, 25, resource("fontIcon.png"));
        fontButton.addActionListener(e -> fontButtonAction());

        imageButton = new IImageButton(25, 25, resource("imageIcon.png"));
        imageButton.addActionListener(e -> imageButtonAction());


        drawPadButton = new IImageButton(25, 25, resource("drawPadIcon.png"));
        drawPadButton.addActionListener(e -> drawPadAction());

        fileButton = new IImageButton(25, 25, resource("fileIcon.png"));
        fileButton.addActionListener(e -> fileAction());

        reconstruct();

    }

    public void fileAction() { }

    private void reconstruct() {
        this.removeAll();
        if (fontEnabled) this.addComp(fontButton);
        if (imageEnabled) this.addComp(imageButton);
        if (drawPadEnabled) this.addComp(drawPadButton);
        if (fileEnabled) this.addComp(fileButton);
        this.revalidate();
    }

    public void drawPadAction() {
        MiniDrawPad drawPad = new MiniDrawPad("MiniDrawPad", true);
        drawPad.addInsertActionListener(f -> {
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
            editor.getTextPane().insertIcon(pos, drawPad.generateImageIcon());
        });
    }

    public void imageButtonAction() {
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
        IImageIcon[] iconList = new IImageIcon[list.length];
        for (int i = 0; i < list.length; i++) {
            iconList[i] = new IImageIcon(list[i].getPath());
        }
        editor.getTextPane().insertIcon(pos, iconList);
    }

    public void fontButtonAction() {
        editor.setToolPanelVisibility(!editor.getToolPanelVisibility());
        editor.revalidate();
    }

}
