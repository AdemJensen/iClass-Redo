package top.chorg.window.foundation.notice;

import top.chorg.window.foundation.IFrame;
import top.chorg.window.foundation.IPanel;
import top.chorg.window.foundation.ITextPane;
import top.chorg.window.foundation.form.IFormButtonPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class INoticeFrame extends IFrame {
    public int width, height;
    public int lineLength;
    private ITextPane textPanel;
    private IFormButtonPanel buttonPanel;
    private JScrollPane scrollPane;
    private boolean scrollable;

    private JLabel[] labels;

    public INoticeFrame(
            String title, String content, boolean scrollable, String...buttonText
    ) {
        super(1, 1, title, null, JFrame.DISPOSE_ON_CLOSE);
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);
        this.setLayout(layout);
        this.setResizable(false);

        this.scrollable = scrollable;

        this.textPanel = new ITextPane();
        this.textPanel.setEditable(false);
        this.textPanel.setBackground(this.getBackground());

        this.scrollPane = new JScrollPane(textPanel);
        this.scrollPane.setBorder(null);
        this.scrollPane.setBackground(this.getBackground());

        this.buttonPanel = new IFormButtonPanel(260, 35, null, buttonText);

        if (scrollable) {
            this.addComp(scrollPane, buttonPanel);
        } else {
            this.addComp(textPanel, buttonPanel);
        }

        this.setText(content);
    }

    public INoticeFrame(String title, String content, String...buttonText) {
        this(title, content, false, buttonText);
    }

    public void setText(String str) {
        this.textPanel.setText(str);
        this.textPanel.trimWidth(600);
        int tagHeight = textPanel.getTargetHeight();
        if (scrollable) {
            scrollPane.setPreferredSize(new Dimension(
                    textPanel.getTargetWidth() + 20,
                    Math.min(tagHeight, 500)
            ));
            width = Math.max(textPanel.getTargetWidth() + 40, 300);
            height = Math.min(tagHeight + 25 + 55, 500 + 25 + 55);
        } else {
            width = Math.max(textPanel.getTargetWidth() + 20, 280);
            height = Math.min(tagHeight + 25 + 55, 500 + 25 + 55);
        }
        this.setSize(new Dimension(width, height));
        this.setLocationCenter(width, height);
    }

    public void addActionListeners(ActionListener...listeners) {
        this.buttonPanel.addActionListeners(listeners);
    }

    @Override
    public void showWindow() {
        super.showWindow();
        EventQueue.invokeLater(() -> {
            this.scrollPane.getVerticalScrollBar().setValue(0);
        });
    }
}
