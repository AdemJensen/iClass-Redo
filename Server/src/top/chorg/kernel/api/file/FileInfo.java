package top.chorg.kernel.api.file;

import top.chorg.support.FileUtils;
import top.chorg.support.TimeUtils;

import java.util.Date;

import static top.chorg.kernel.Variable.authData;

public class FileInfo {

    public int id, owner, targetId, storageType;
    public String name, hash;
    public long size;
    public Date uploadDate;

    public FileInfo(int id, int owner, String name, String hash, long size, Date uploadDate) {
        this.id = id;
        this.owner = owner;
        this.name = name;
        this.hash = hash;
        this.size = size;
        this.uploadDate = uploadDate;
    }

    public String getInfoStr() {
        return String.format(
                "%s，由 %s 于 %s 上传",
                FileUtils.convertFileSize(size), authData.getRealName(owner)[0], TimeUtils.dateToStr(uploadDate)
        );
    }

}
