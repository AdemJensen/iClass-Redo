package top.chorg.kernel;

import com.google.gson.Gson;
import top.chorg.kernel.database.*;
import top.chorg.kernel.server.Client;
import top.chorg.kernel.server.FileServer;
import top.chorg.kernel.server.MasterServer;
import top.chorg.kernel.server.RequestDispatcher;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Random;

public class Variable {

    public static Gson gson = new Gson();
    public static Random random = new Random();
    public static Connection database;

    public static RequestDispatcher requestDispatcher = new RequestDispatcher();
    public static MasterServer masterServer = new MasterServer();
    public static FileServer fileServer = new FileServer();
    public static ArrayList<Client> clients = new ArrayList<>();

    public static AuthData authData = new AuthData();
    public static VoteData voteData = new VoteData();
    public static FileData fileData = new FileData();
    public static ChatData chatData = new ChatData();
    public static AnnounceData announceData = new AnnounceData();

    public static String getRelativePath(String root, String...relativePath) {
        StringBuilder builder = new StringBuilder(root);
        for (String path : relativePath) {
            builder.append(File.separator);
            builder.append(path);
        }
        return builder.toString();
    }

    public static String data(String...relativePath) {
        return getRelativePath("data", relativePath);
    }

    public static String temp(String...relativePath) {
        return getRelativePath("temp", relativePath);
    }

}