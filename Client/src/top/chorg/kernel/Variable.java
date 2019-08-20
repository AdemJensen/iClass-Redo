package top.chorg.kernel;

import com.google.gson.Gson;
import top.chorg.kernel.foundation.Network;
import top.chorg.kernel.network.NetDispatcher;

import javax.swing.text.Style;
import javax.swing.text.StyleContext;
import java.io.File;
import java.util.Date;

public class Variable {

    public static Gson gson = new Gson();
    public static NetDispatcher netDispatcher = new NetDispatcher();
    public static StyleContext styleContext = new StyleContext();
    public static Network masterNet = new Network("127.0.0.1", 8888);
    public static Network fileNet = new Network("127.0.0.1", 8889);
    public static Style defaultStyle = styleContext.getStyle(StyleContext.DEFAULT_STYLE);

    public static String getRelativePath(String root, String...relativePath) {
        StringBuilder builder = new StringBuilder(root);
        for (String path : relativePath) {
            builder.append(File.separator);
            builder.append(path);
        }
        return builder.toString();
    }

    public static String resource(String...relativePath) {
        return getRelativePath("resource", relativePath);
    }

    public static String temp(String...relativePath) {
        return getRelativePath("temp", relativePath);
    }

    public static String download(String...relativePath) {
        return getRelativePath("download", relativePath);
    }

}
