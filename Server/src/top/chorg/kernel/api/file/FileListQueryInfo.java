package top.chorg.kernel.api.file;

public class FileListQueryInfo {

    public int curPage, totalPage;
    public FileInfo[] infos;

    public FileListQueryInfo(int curPage, int totalPage, FileInfo[] infos) {
        this.curPage = curPage;
        this.totalPage = totalPage;
        this.infos = infos;
    }
}
