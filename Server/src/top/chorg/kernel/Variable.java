package top.chorg.kernel;

import com.google.gson.Gson;
import top.chorg.kernel.database.*;
import top.chorg.kernel.server.Client;
import top.chorg.kernel.server.FileServer;
import top.chorg.kernel.server.MasterServer;
import top.chorg.kernel.server.RequestDispatcher;
import top.chorg.kernel.server.dispatch.AuthDispatch;
import top.chorg.kernel.server.dispatch.FileDispatch;

import java.io.File;
import java.sql.Connection;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class Variable {

    public static Gson gson = new Gson();
    public static Random random = new Random();
    public static Connection database;

    public static RequestDispatcher requestDispatcher = new RequestDispatcher();
    public static MasterServer masterServer = new MasterServer();
    public static FileServer fileServer = new FileServer();
    public static ConcurrentHashMap<Integer, Client> clients = new ConcurrentHashMap<>();

    public static AuthData authData = new AuthData();
    public static VoteData voteData = new VoteData();
    public static FileData fileData = new FileData();
    public static ChatData chatData = new ChatData();
    public static AnnounceData announceData = new AnnounceData();

    public static AuthDispatch authDispatch = new AuthDispatch();
    public static FileDispatch fileDispatch = new FileDispatch();

    public static String getRelativePath(String root, String...relativePath) {
        StringBuilder builder = new StringBuilder(root);
        for (String path : relativePath) {
            builder.append(File.separator);
            builder.append(path);
        }
        File temp = new File(builder.toString()).getParentFile();
        if (!temp.isDirectory()) temp.mkdirs();
        return builder.toString();
    }

    public static String getServerFileName(String hash) {
        return String.format("file@%s", hash);
    }

    public static String getServerAvatarName(int type, int targetId) {
        return String.format((type == 1 ? "ua@%d.png" : "ga@%d.png"), targetId);
    }

    public static String data(String...relativePath) {

        return getRelativePath("data", relativePath);
    }

    public static String temp(String...relativePath) {
        return getRelativePath("temp", relativePath);
    }

}