package top.chorg.window.foundation;

import top.chorg.window.foundation.form.IFormButtonPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class INoticeFrame extends IFrame {
    public int width, height;
    public int lineLength;
    public String origin;
    private String[] processedText;
    private IPanel textPanel;
    private IFormButtonPanel buttonPanel;

    private JLabel[] labels;

    public INoticeFrame(
            String title, String content, int lineLength,
            String...buttonText
    ) {
        super(1, 1, title, new FlowLayout(FlowLayout.CENTER), JFrame.DISPOSE_ON_CLOSE);
        this.lineLength = lineLength;
        this.origin = content;
        this.textPanel = new IPanel(
                1, 1,
                new EmptyBorder(5, 20, 0, 20),
                new FlowLayout(FlowLayout.LEFT)
        );
        this.processText();
        this.assignProcessedData();
        this.refreshFrameSize();
        this.buttonPanel = new IFormButtonPanel(
                260, 35,
                new EmptyBorder(5, 20, 0, 20),
                buttonText
        );
        this.addComp(textPanel);
        this.addComp(buttonPanel);
        this.setLocation(370, 240);
    }

    public INoticeFrame(String title, String content, String...buttonText) {
        this(title, content, 40, buttonText);
    }

    public void setText(String str) {
        this.origin = str;
        this.processText();
        this.textPanel.removeAll();
        assignProcessedData();
        this.refreshFrameSize();
    }

    public void addActionListeners(ActionListener...listeners) {
        this.buttonPanel.addActionListeners(listeners);
    }

    private void refreshFrameSize() {
        int textPanelWidth = Math.max(lineLength * (labels[0].getFont().getSize() / 2 + 2) + 40, 300);
        int textPanelHeight = (int) (labels[0].getFont().getSize() * 1.3 + 5) * labels.length + 20;

        this.textPanel.setPreferredSize(new Dimension(textPanelWidth, textPanelHeight));

        width = textPanelWidth + 70;
        height = textPanelHeight + 75;
        this.setSize(new Dimension(width, height));
    }

    private void assignProcessedData() {
        labels = new JLabel[processedText.length];
        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel(processedText[i]);
            labels[i].setPreferredSize(new Dimension(
                    Math.max(lineLength * (labels[i].getFont().getSize() / 2 + 2), 260) + 2,
                    (int) (labels[i].getFont().getSize() * 1.3)
            ));
        }
        textPanel.addComp(labels);
    }

    private void processText() {
        double curLen = 0;
        double lineLength = this.lineLength;
        ArrayList<String> resTemp = new ArrayList<>();
        StringBuilder curResTemp = new StringBuilder();
        String chinese = "[\u4e00-\u9fa5]";     // 判断汉字的正则表达式
        for (int i = 0; i < origin.length(); i++) {
            String temp = origin.substring(i, i + 1);
            if (temp.matches(chinese)) {
                curLen += 1.7;
                curResTemp.append(origin.charAt(i));
            } else if (origin.charAt(i) == '\n') {
                resTemp.add(curResTemp.toString());
                curResTemp = new StringBuilder();
                curLen = 0;
            } else if (origin.charAt(i) == 'i' || origin.charAt(i) == 'j' || origin.charAt(i) == 'l') {
                curLen += 0.5;
                curResTemp.append(origin.charAt(i));
            } else if (origin.charAt(i) == 'f' || origin.charAt(i) == 't') {
                curLen += 0.63;
                curResTemp.append(origin.charAt(i));
            } else if (Character.isLowerCase(origin.charAt(i))) {
                curLen += 1;
                curResTemp.append(origin.charAt(i));
            } else {
                curLen += 1.2;
                curResTemp.append(origin.charAt(i));
            }
            if (curLen >= lineLength) {
                resTemp.add(curResTemp.toString());
                curResTemp = new StringBuilder();
                curLen = 0;
            }
        }
        if (curLen > 0) {
            resTemp.add(curResTemp.toString());
        }
        processedText = new String[resTemp.size()];
        resTemp.toArray(processedText);
    }

}
