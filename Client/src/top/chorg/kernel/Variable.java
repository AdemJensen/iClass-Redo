package top.chorg.kernel;

import com.google.gson.Gson;
import top.chorg.kernel.api.auth.UserInfo;
import top.chorg.kernel.foundation.MasterNetwork;
import top.chorg.kernel.foundation.Network;
import top.chorg.kernel.network.*;
import top.chorg.support.TimeUtils;
import top.chorg.window.file.FileTaskFrame;
import top.chorg.window.foundation.IImageIcon;
import top.chorg.window.foundation.notice.IInformationFrame;
import top.chorg.window.index.MasterFrame;

import javax.swing.text.Style;
import javax.swing.text.StyleContext;
import java.io.File;
import java.util.Date;

public class Variable {

    public static Gson gson = new Gson();
    public static NetDispatcher netDispatcher = new NetDispatcher();
    public static StyleContext styleContext = new StyleContext();
    public static MasterNetwork masterCon = new MasterNetwork();
    public static Style defaultStyle = styleContext.getStyle(StyleContext.DEFAULT_STYLE);

    public static UserInfo self = null;

    public static AuthNet authNet = new AuthNet();
    public static VoteNet voteNet = new VoteNet();
    public static FileNet fileNet = new FileNet();
    public static ChatNet chatNet = new ChatNet();
    public static AnnounceNet announceNet = new AnnounceNet();

    public static FileTaskFrame fileTaskFrame = new FileTaskFrame();
    public static MasterFrame masterFrame;

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

    public static String resource(String...relativePath) {
        return getRelativePath("resource", relativePath);
    }

    public static String temp(String...relativePath) {
        return getRelativePath("temp", relativePath);
    }

    public static String download(String...relativePath) {
        return getRelativePath("download", relativePath);
    }

    public static String getStorageAvatarName(int type, int targetId) {
        return String.format((type == 1 ? "ua@%d.png" : "ga@%d.png"), targetId);
    }

    public static IImageIcon getAvatar(int type, int targetId) {
        IImageIcon avatar = new IImageIcon(temp(getStorageAvatarName(type, targetId)));
        if (!avatar.isValid()) {
            if (type == 1) avatar = new IImageIcon(resource("defaultUserIcon.png"));
            else avatar = new IImageIcon(resource("defaultGroupIcon.png"));
        }
        return avatar;
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

    public static String getFileIconName(String name) {
        name = name.toLowerCase();
        if (name.endsWith(".xls") || name.endsWith(".xlsx")) return "excel";
        if (name.endsWith(".bmp") || name.endsWith(".png") || name.endsWith(".jpg") ||
                name.endsWith(".jpeg") || name.endsWith(".gif")) return "img";
        if (name.endsWith(".wav") || name.endsWith(".mp3") || name.endsWith(".ogg") || name.endsWith(".cda") ||
                name.endsWith(".ra") || name.endsWith(".midi") || name.endsWith(".ape") || name.endsWith(".flac") ||
                name.endsWith(".aac") || name.endsWith(".mkv")) return "music";
        if (name.endsWith(".pdf")) return "pdf";
        if (name.endsWith(".ppt") || name.endsWith(".pptx")) return "ppt";
        if (name.endsWith(".doc") || name.endsWith(".docx")) return "word";
        if (name.endsWith(".mp4") || name.endsWith(".avi") || name.endsWith(".wma") || name.endsWith(".rmvb") ||
                name.endsWith(".rm") || name.endsWith(".flash") || name.endsWith(".mid") || name.endsWith(".3gp"))
            return "video";
        if (name.endsWith(".apk")) return "apk";
        if (name.endsWith(".exe")) return "exe";
        if (name.endsWith(".txt")) return "txt";
        if (name.endsWith(".java") || name.endsWith(".php") || name.endsWith(".html") || name.endsWith(".htm") ||
                name.endsWith(".cpp") || name.endsWith(".c") || name.endsWith(".obj") || name.endsWith(".h") ||
                name.endsWith(".hpp") || name.endsWith(".perl") || name.endsWith(".py") || name.endsWith(".kt") ||
                name.endsWith(".cs") || name.endsWith(".qt") || name.endsWith(".ini") || name.endsWith(".conf") ||
                name.endsWith(".jar")) return "code";
        return "unknown";
    }

}