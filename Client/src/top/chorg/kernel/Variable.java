package top.chorg.kernel;

import com.google.gson.Gson;
import top.chorg.kernel.network.NetDispatcher;

import java.io.File;

public class Variable {

    public static Gson gson = new Gson();
    public static NetDispatcher netDispatcher = new NetDispatcher();

    public static String resource(String...relativePath) {
        StringBuilder builder = new StringBuilder("resource");
        for (String path : relativePath) {
            builder.append(File.separator);
            builder.append(path);
        }
        return builder.toString();
    }

    public static String temp(String...relativePath) {
        StringBuilder builder = new StringBuilder("temp");
        for (String path : relativePath) {
            builder.append(File.separator);
            builder.append(path);
        }
        return builder.toString();
    }

}
