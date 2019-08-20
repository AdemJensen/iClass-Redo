package top.chorg.kernel;

import com.google.gson.Gson;
import top.chorg.kernel.network.NetDispatcher;

import javax.swing.text.Style;
import javax.swing.text.StyleContext;
import java.io.File;
import java.util.Date;

public class Variable {

    public static Gson gson = new Gson();
    public static NetDispatcher netDispatcher = new NetDispatcher();
    public static StyleContext styleContext = new StyleContext();
    public static Style defaultStyle = styleContext.getStyle(StyleContext.DEFAULT_STYLE);

    public static String getRelativePath(String root, String...relativePath) {
        StringBuilder builder = new StringBuilder("root");
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

    public static String getTimeDurText(Date date) {
        long curStamp = new Date().getTime();
        long diffMills = curStamp - date.getTime();
        diffMills /= 1000;  // 得到秒
        if (diffMills < 60) return "刚刚";
        diffMills /= 60;    // 得到分钟
        if (diffMills < 60) return String.format("%d分钟前", diffMills);
        diffMills /= 60;    // 得到小时
        if (diffMills < 24) return String.format("%d小时前", diffMills);
        diffMills /= 24;    // 得到天
        if (diffMills < 30) return String.format("%d天前", diffMills);
        diffMills /= 30;    // 得到月
        if (diffMills < 12) return String.format("%d月前", diffMills);
        diffMills /= 12;    // 得到年
        return String.format("%d年前", diffMills);
    }

}
