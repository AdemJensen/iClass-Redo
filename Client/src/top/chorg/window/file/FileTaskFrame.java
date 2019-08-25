package top.chorg.window.file;

import top.chorg.kernel.foundation.FileNetwork;
import top.chorg.window.foundation.IPagedListFrame;

import java.util.ArrayList;

public class FileTaskFrame extends IPagedListFrame {

    private ArrayList<FileTaskLabel> list;
    private final int perPage = 8;

    public FileTaskFrame() {
        super(480, 520, "文件传输任务管理");
        list = new ArrayList<>();
        setTotalPage(99999999);
        setPageNum(1);
    }

    @Override
    public void setPageNum(int page) {
        super.setPageNum(page);
        this.clearList();
        if (list.size() == 0) {
            this.setTotalPage(0);
            return;
        }
        int sta = Math.min((page - 1) * perPage + 1, list.size());
        int end = Math.min(sta + perPage, list.size());
        this.setTotalPage((int) Math.ceil((double) list.size() / perPage));
        for (int i = sta; i < end; i++) {
            this.addItem(list.get(i));
        }
    }

    public void addItem(String name, long size, int taskType) {
        list.add(new FileTaskLabel(470, name, this, new FileNetwork(name, size, taskType)));
    }

    public void reload() {
        this.setPageNum(getPageNum());
    }
}
