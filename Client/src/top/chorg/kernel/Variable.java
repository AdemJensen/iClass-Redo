package top.chorg.kernel;

import com.google.gson.Gson;
import top.chorg.kernel.network.NetDispatcher;

import java.io.File;
import java.util.Date;

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
