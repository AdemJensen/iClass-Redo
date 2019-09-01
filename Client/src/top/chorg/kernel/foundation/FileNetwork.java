package top.chorg.kernel.foundation;

import top.chorg.kernel.foundation.enumClass.FileTask;
import top.chorg.support.FileUtils;
import top.chorg.window.file.FileTaskLabel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileNetwork extends Network {

    private boolean ongoing = false;
    private boolean canceled = false;

    private int taskType; // 建议使用FileTask类
    private String fileName, authCode, localPath;   // fileName: 服务器端存储名
    private long size = 0;

    private FileTaskLabel display = null;

    public FileNetwork(String fileName, String authCode, String localPath, int taskType) {
        super("127.0.0.1", 8889);
        this.fileName = fileName;
        this.authCode = authCode;
        this.localPath = localPath;
        this.taskType = taskType;
    }

    public void setDisplay(FileTaskLabel label) {
        this.display = label;
    }

    public String startMission() {
        if (!connect()) {
            setDisplayStr("无法连接服务器");
            return "无法连接文件服务器";
        }
        printWriter.println(authCode);
        printWriter.flush();
        try {
            if (taskType == FileTask.FT_UPLOAD) return uploadFile();
            else return downloadFile();
        } catch (Exception e) {
            disconnect();
            setDisplayStr("错误：" + e.getMessage());
            return "错误：" + e.getMessage();
        }
    }

    private void setDisplayStr(String str) {
        if (display != null) display.showStatusLabel(str);
    }

    private void setDisplayBarStr(int progress, String str) {
        if (display != null) {
            display.showProgressBar(progress, str);
        }
    }

    private String uploadFile() {
        printWriter.println("upload");
        printWriter.flush();
        this.size = new File(localPath).length();
        boolean isOk = false;
        long process = 0;
        try {
            var fin = new FileInputStream(localPath);
            var bytes = new byte[1024];
            var os = socket.getOutputStream();
            for (int length; (length = fin.read(bytes)) != -1; ) {
                os.write(bytes, 0, length);
                process += length;
                setDisplayBarStr(
                        (int) (process / size),
                        String.format("%s / %s", FileUtils.convertFileSize(process), FileUtils.convertFileSize(size))
                );
            }
            isOk = true;
            setDisplayStr("上传完成");
            socket.close();
            fin.close();
        } catch (Exception e) {
            if (!isOk) setDisplayStr("上传失败");
            return "上传过程出现错误";
        }
        return "";
    }

    public boolean cancelMission() {
        if (!ongoing) return false;
        canceled = true;
        return true;
    }

    @Override
    protected void listenerThreadAction() { }

    private String downloadFile() throws Exception {
        printWriter.println("download");
        printWriter.flush();
        this.size = Long.parseLong(bufferedReader.readLine());
        boolean isOk = false;
        long process = 0;
        try {
            var fo = new FileOutputStream(localPath);
            var bytes = new byte[1024];
            for (int length; (length = socket.getInputStream().read(bytes)) != -1; ) {
                fo.write(bytes, 0, length);
                process += length;
                setDisplayBarStr(
                        (int) (process / size),
                        String.format("%s / %s", FileUtils.convertFileSize(process), FileUtils.convertFileSize(size))
                );
            }
            isOk = true;
            setDisplayStr("下载完成");
            fo.close();
            socket.close();
        } catch (Exception e) {
            if (!isOk) setDisplayStr("下载失败");
            return "下载过程出现错误";
        }
        return "";
    }

    public int getTaskType() {
        return taskType;
    }

}
