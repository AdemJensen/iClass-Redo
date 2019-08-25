package top.chorg.kernel.foundation;

public class FileNetwork extends Network {

    private boolean ongoing = false;
    private boolean canceled = false;

    private int taskType; // 0 = 上传，1 = 下载
    private String name;
    private long size;

    public FileNetwork(String name, long size, int taskType) {
        super("127.0.0.1", 8889);
        this.name = name;
        this.size = size;
        this.taskType = taskType;
    }

    public String uploadFile(String localPath) {
        if (!this.connect()) return "无法连接到文件服务器";

        return "";
    }

    public boolean cancelMission() {
        if (!ongoing) return false;
        canceled = true;
        return true;
    }

    public String downloadFile(String code) {
        if (!this.connect()) return "无法连接到文件服务器";

        return "";
    }

    public int getTaskType() {
        return taskType;
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }
}
