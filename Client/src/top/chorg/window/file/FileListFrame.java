package top.chorg.window.file;

import top.chorg.kernel.api.file.FileInfo;
import top.chorg.kernel.api.file.FileListQueryInfo;
import top.chorg.window.foundation.IImageIcon;
import top.chorg.window.foundation.IPagedListFrame;
import top.chorg.window.foundation.IPanel;
import top.chorg.window.foundation.button.IImageButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

import static top.chorg.kernel.Variable.*;

public class FileListFrame extends IPagedListFrame {

    private int listType, targetId;

    private IPanel toolPanel;
    private IImageButton upload, task, refresh;

    /**
     * 创建文件列表对象
     *
     * @param listType 区别本列表是用户之间的列表还是班级列表，
     *                 0 = 班级列表
     *                 1 = 用户列表
     * @param targetId 文件发送目标
     *                 若type = 0，则targetId为班级编号
     *                 若type = 1，则targetId为目标用户编号
     */
    public FileListFrame(int listType, int targetId) {
        super(480, 510, "文件列表");
        this.getMasterPanel().setPreferredSize(new Dimension(480, 510 - 60 - 40));
        this.toolPanel = new IPanel(480, 40,
                new EmptyBorder(0, 0, 0, 15), new FlowLayout(FlowLayout.RIGHT));
        this.toolPanel.setBackground(Color.WHITE);
        this.upload = new IImageButton(30, 30, resource("file", "upload.png"));
        this.upload.addActionListener(e -> {

        });
        this.task = new IImageButton(30, 30, resource("file", "task.png"));
        this.task.addActionListener(e -> {
            fileTaskFrame.showWindow();
        });
        this.refresh = new IImageButton(30, 30, resource("file", "refresh.png"));
        this.refresh.addActionListener(e -> {
            reload();
        });
        this.toolPanel.addComp(upload, task, refresh);

        this.add(toolPanel, 0);

        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.listType = listType;
        this.targetId = targetId;
        setTotalPage(99999999);
        setPageNum(1);
    }

    @Override
    public void setPageNum(int page) {
        super.setPageNum(page);
        FileListQueryInfo newInfo = fileNet.getFileList(listType, targetId, page);
        this.setTotalPage(newInfo.totalPage);
        this.clearList();
        for (FileInfo listInfo : newInfo.infos) {
            this.addItem(new FileListLabel(470, listInfo, this, listType, targetId));
        }
    }

    public void reload() {
        this.setPageNum(getPageNum());
    }

}
