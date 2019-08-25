package top.chorg.kernel.network;

import top.chorg.kernel.api.file.FileInfo;
import top.chorg.kernel.api.file.FileListQueryInfo;

import java.util.Date;

public class FileNet {

    /**
     * 获取文件列表
     * @param type 要获取的是用户之间的列表还是班级列表，
     *             0 = 班级列表
     *             1 = 用户列表
     * @param targetId 文件发送目标
     *                 若type = 0，则targetId为班级编号
     *                 若type = 1，则targetId为目标用户编号
     * @return 获取到的用户列表
     */
    public FileListQueryInfo getFileList(int type, int targetId, int page) {
        return new FileListQueryInfo(page, 3, new FileInfo[] {
                new FileInfo(1, 1, "file1.png", "01", 100000000, new Date()),
                new FileInfo(2, 2, "file2.cpp", "02", 100000000, new Date()),
                new FileInfo(3, 3, "file3.py", "03", 100000000, new Date()),
                new FileInfo(4, 4, "file4.mp4", "04", 100000000, new Date()),
                new FileInfo(5, 5, "file5.obj", "05", 100000000, new Date()),
                new FileInfo(6, 6, "file6.flash", "06", 100000000, new Date()),
                new FileInfo(7, 6, "file7.flash", "06", 100000000, new Date())
        });
    }

    public String uploadFile(String path) {
        // TODO
        return "";
    }

    public String downloadFile(int id, String localPath) {
        // TODO
        return "";
    }

    public String uploadImage(String path) {
        // TODO
        return "";
    }

    public String downloadImage(String hash) {
        // TODO
        return "";
    }

    /**
     * 从服务器下载文件，基准操作
     * @param hash 文件的hash值
     * @param localPath 存储在本地的位置
     * @return 若为空字符串，则下载成功加入队列，否则是有问题
     */
    private String downloadFile(String hash, String localPath) {
        return "";
    }

    /**
     * 向服务器上传文件，基准操作
     * @param hash 文件的hash值
     * @param localPath 存储在本地的位置
     * @return 若为空字符串，则下载成功加入队列，否则是有问题
     */
    private String uploadFile(String hash, String localPath) {
        return "";
    }

    public String removeFileFromList(int fileId) {
        return "";
    }

}
