package top.chorg.kernel.server.dispatch;

import com.google.gson.JsonParseException;
import top.chorg.kernel.api.file.FileDownloadRequest;
import top.chorg.kernel.api.file.FileUploadRequest;
import top.chorg.support.MD5;

import java.util.Date;

import static top.chorg.kernel.Variable.*;

public class FileDispatch {

    public String upload(int selfId, String obj) throws JsonParseException {
        FileUploadRequest request = gson.fromJson(obj, FileUploadRequest.class);
        String code = MD5.encode(obj + new Date());
        if (request.target > 0 && request.type > 0) {
            fileServer.register(code, selfId, request.target,
                    request.type, request.oriName, request.hash);
        } else {
            fileServer.register(code, request.storageName);
        }
        System.out.println("[DEBUG] Generated code: " + code);
        return code;
    }

    public String download(String obj) throws JsonParseException {
        FileDownloadRequest request = gson.fromJson(obj, FileDownloadRequest.class);
        String code = MD5.encode(obj + new Date());
        if (request.fileId > 0) {
            fileServer.register(code, getServerFileName(fileData.getFileHash(request.fileId)));
        } else {
            fileServer.register(code, request.storageName);
        }
        return code;
    }

}
