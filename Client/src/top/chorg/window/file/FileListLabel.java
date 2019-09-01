package top.chorg.window.file;

import top.chorg.kernel.api.file.FileInfo;
import top.chorg.window.foundation.IClickableAdapter;
import top.chorg.window.foundation.IImageIcon;
import top.chorg.window.foundation.IPanel;
import top.chorg.window.foundation.button.ILinkedButton;
import top.chorg.window.foundation.notice.IConfirmNoticeFrame;
import top.chorg.window.foundation.notice.IInformationFrame;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import static top.chorg.kernel.Variable.*;

public class FileListLabel extends IPanel {

    public FileListFrame list;
    public int listType, targetId;
    public ILinkedButton download, delete;
    public IImageIcon fileIcon;
    public IPanel rightPanel;
    public JLabel iconLabel, fileNameLabel, infoLabel;

    public FileListLabel(int width, FileInfo info, FileListFrame list, int listType, int targetId) {
        super(width, 50, null, new FlowLayout(FlowLayout.LEFT));
        this.listType = listType;
        this.targetId = targetId;
        this.list = list;

        this.fileIcon = new IImageIcon(resource("file", getFileIconName(info.name) + ".png"));
        this.fileIcon.setSize(35, 35);
        this.iconLabel = new JLabel(fileIcon);
        this.iconLabel.setPreferredSize(new Dimension(35, 35));

        this.rightPanel = new IPanel(width - 50, 40);
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
        layout.setVgap(0);
        layout.setHgap(0);
        rightPanel.setLayout(layout);
        rightPanel.setOpaque(false);

        this.fileNameLabel = new JLabel(info.name);
        this.fileNameLabel.setPreferredSize(new Dimension(width - 130, 20));
        this.fileNameLabel.setFont(new Font("宋体", Font.PLAIN, 16));

        this.infoLabel = new JLabel(info.getInfoStr());
        this.infoLabel.setPreferredSize(new Dimension(width - 50, 20));
        this.infoLabel.setFont(new Font("宋体", Font.PLAIN, 13));
        this.infoLabel.setForeground(Color.GRAY);

        this.download = new ILinkedButton("下载");
        this.download.setPreferredSize(new Dimension(35, 20));
        this.download.addActionListener(e -> {
            try {
                FileDialog dialog = new FileDialog(new Frame(), "选择保存位置", FileDialog.SAVE);
                dialog.setFile(info.name);
                dialog.setMultipleMode(false);
                dialog.setVisible(true);
                fileNet.downloadFile(info.id, dialog.getDirectory() + File.separator + info.name);
            } catch (Exception e1) {
                new IInformationFrame("错误", "数据加载失败！").showWindow();
            }
        });

        rightPanel.addComp(fileNameLabel, download);
        if ((listType == 0 && authNet.getLevelInGroup(targetId, self.id)[0] > 1) || self.id == info.owner) {
            this.delete = new ILinkedButton("删除");
            this.delete.setPreferredSize(new Dimension(35, 20));
            this.delete.addActionListener(e -> new IConfirmNoticeFrame(
                    "移除文件\"" + info.name + "\"",
                    e1 -> {
                        checkResult(
                                fileNet.removeFileFromList(info.id),
                                "已成功移除文件\"" + info.name + "\""
                        );
                        list.reload();
                    }
            ).showWindow());
            rightPanel.addComp(this.delete);
        }
        rightPanel.addComp(infoLabel);
        this.addComp(iconLabel, rightPanel);

        this.addMouseListener(new IClickableAdapter(this));

    }

}
