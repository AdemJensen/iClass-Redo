package top.chorg.kernel;

import com.google.gson.Gson;
import top.chorg.kernel.api.UserInfo;
import top.chorg.kernel.foundation.Network;
import top.chorg.kernel.network.AnnounceNet;
import top.chorg.kernel.network.AuthNet;
import top.chorg.kernel.network.NetDispatcher;
import top.chorg.kernel.network.VoteNet;
import top.chorg.support.TimeUtils;
import top.chorg.window.foundation.notice.IInformationFrame;

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

    public static UserInfo self = null;

    public static AuthNet authNet = new AuthNet();
    public static VoteNet voteNet = new VoteNet();
    public static AnnounceNet announceNet = new AnnounceNet();

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

    public static String getAnnounceEditInfoStr(Date publishDate, Date editDate, int uPub, int uEdit) {
        String inf;
        String[] realNames = authNet.getRealName(uPub, uEdit);
        inf = "由 " + realNames[0] + " 于 " + TimeUtils.dateToStrLong(publishDate) + " 发表";
        if (uEdit == uPub && editDate != publishDate) {
            inf += "，最后编辑于 " + TimeUtils.dateToStrLong(editDate);
        } else if (uEdit != uPub) {
            inf += "，由 " + realNames[1] + " 于 " + TimeUtils.dateToStrLong(editDate) + " 最后编辑";
        }
        return inf;
    }

    public static String getVoteTimeInfoStr(Date endDate) {
        return "将于 " + TimeUtils.dateToStrLong(endDate) + " 结束";
    }

    public static String getVoteEditInfoStr(Date publishDate, Date editDate, int uPub, int uEdit) {
        String inf;
        String[] realNames = authNet.getRealName(uPub, uEdit);
        inf = "由 " + realNames[0] + " 于 " + TimeUtils.dateToStrLong(publishDate) + " 发起";
        if (uEdit == uPub && editDate != publishDate) {
            inf += "，最后编辑于 " + TimeUtils.dateToStrLong(editDate);
        } else if (uEdit != uPub) {
            inf += "，由 " + realNames[1] + " 于 " + TimeUtils.dateToStrLong(editDate) + " 最后编辑";
        }
        return inf;
    }

    public static void checkResult(String str, String successNotice) {
        if (str.length() == 0) {
            new IInformationFrame(
                    "成功", successNotice
            ).showWindow();
        } else {
            new IInformationFrame(
                    "失败", "错误：" + str
            ).showWindow();
        }
    }

}
