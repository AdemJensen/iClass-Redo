package top.chorg.window.file;

import top.chorg.kernel.foundation.FileNetwork;
import top.chorg.window.foundation.IClickableAdapter;
import top.chorg.window.foundation.IImageIcon;
import top.chorg.window.foundation.IPanel;
import top.chorg.window.foundation.button.ILinkedButton;
import top.chorg.window.foundation.notice.IInformationFrame;

import javax.swing.*;
import java.awt.*;

import static top.chorg.kernel.Variable.*;

public class FileTaskLabel extends IPanel {

    public FileTaskFrame list;
    public FileNetwork fileCon;
    public ILinkedButton cancel;
    public IImageIcon fileIcon;
    public IPanel rightPanel;
    public JLabel iconLabel, fileNameLabel, statusLabel;
    public JProgressBar bar;

    private boolean curDisplayLabel = true;

    public FileTaskLabel(int width, String fileName, FileTaskFrame list, FileNetwork fileCon) {
        super(width, 50, null, new FlowLayout(FlowLayout.LEFT));
        this.list = list;
        this.fileCon = fileCon;

        this.fileIcon = new IImageIcon(resource("file", getFileIconName(fileName) + ".png"));
        this.fileIcon.setSize(35, 35);
        this.iconLabel = new JLabel(fileIcon);
        this.iconLabel.setPreferredSize(new Dimension(35, 35));

        this.rightPanel = new IPanel(width - 50, 40);
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
        layout.setVgap(0);
        layout.setHgap(0);
        rightPanel.setLayout(layout);
        rightPanel.setOpaque(false);

        this.fileNameLabel = new JLabel();
        this.fileNameLabel.setPreferredSize(new Dimension(width - 120, 20));
        this.fileNameLabel.setFont(new Font("宋体", Font.PLAIN, 16));

        this.statusLabel = new JLabel("正在开始");
        this.statusLabel.setPreferredSize(new Dimension(width - 50, 20));
        this.statusLabel.setFont(new Font("宋体", Font.PLAIN, 13));
        this.statusLabel.setForeground(Color.GRAY);

        this.addComp(this.fileNameLabel);
        this.cancel = new ILinkedButton("取消");
        this.cancel.setPreferredSize(new Dimension(35, 20));
        this.cancel.addActionListener(e -> {
            try {
                if (!fileCon.cancelMission()) throw new Exception();
            } catch (Exception e1) {
                new IInformationFrame("错误", "无法停止下载任务！").showWindow();
            }
        });
        rightPanel.addComp(fileNameLabel, statusLabel);
        this.addComp(iconLabel, rightPanel);

        this.bar = new JProgressBar();
        this.bar.setPreferredSize(new Dimension(width - 50, 20));
        this.bar.setValue(0);

        this.addMouseListener(new IClickableAdapter(this));

    }

    public void showStatusLabel(String label) {
        if (!curDisplayLabel) {
            this.rightPanel.remove(bar);
            this.rightPanel.add(statusLabel);
            this.revalidate();
            curDisplayLabel = true;
        }
        this.statusLabel.setText(label);
    }

    public void showProgressBar(int progress, String text) {
        if (curDisplayLabel) {
            this.rightPanel.remove(statusLabel);
            this.rightPanel.add(bar);
            this.revalidate();
            curDisplayLabel = false;
        }
        bar.setValue(progress);
        bar.setString(text);
    }

    public void setCancelButtonVisibility(boolean visibility) {
        this.cancel.setVisible(visibility);
    }

    public FileNetwork getFileCon() {
        return fileCon;
    }

}
