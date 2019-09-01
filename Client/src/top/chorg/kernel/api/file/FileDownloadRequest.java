package top.chorg.kernel.api.file;

public class FileDownloadRequest {

    public String storageName;
    public int fileId;

    public FileDownloadRequest(String storageName) {
        this.storageName = storageName;
        fileId = -1;
    }

    public FileDownloadRequest(int fileId) {
        this.storageName = "";
        this.fileId = fileId;
    }
}
