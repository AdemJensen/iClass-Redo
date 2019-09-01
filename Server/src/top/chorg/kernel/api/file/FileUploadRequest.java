package top.chorg.kernel.api.file;

public class FileUploadRequest {

    public String storageName, oriName, hash;
    public int type, target;

    public FileUploadRequest(String storageName) {
        this.storageName = storageName;
        type = target = -1;
    }

    public FileUploadRequest(int type, int target, String oriName, String hash) {
        this.type = type;
        this.target = target;
        this.oriName = oriName;
        this.hash = hash;
    }
}
