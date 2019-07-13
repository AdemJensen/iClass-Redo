package top.chorg.tests.windowTests;

import top.chorg.window.foundation.INoticeFrame;

public class INoticeFrameTest {
    public static void main(String[] args) {

        longTest();

    }

    public static void longTest() {
        new INoticeFrame(
                "Test",
                "This is a test.\n" +
                        "This is a fucking test by the hahahahahadrftyguhjlkhgfdrtyughijknbvgcfgdrftyguhijlknmb vcfdxswedr5t6yiuop[lhhhhhhhhhhhhhahahahahhhhhahahhhhhhhhhhhhhhhhhahahahahhhhhahahhahahhhhhhhhhhhhhhhhhhhhhhhhhahahahahhhhhahahhhhhhhhhhhhhhhhhahahahahhhhhahahhahahhhhhhhhhhhhhhhhhhhhhhhhhahahahahhhhhahahhhhhhhhhhhhhhhhhahahahahhhhhahahhahahhhhhhhhhhhhhhhhhhhhhhhhhahahahahhhhhahahhhhhhhhhhhhhhhhhahahahahhhhhahahhahahhhhhhhhhhhhhhhhhhhhhhhhhahahahahhhhhahahhhhhhhhhhhhhhhhhahahahahhhhhahahhahahhhhhhhhhhhhhhhhhhhhhhhhhahahahahhhhhahahhhhhhhhhhhhhhhhhahahahahhhhhahahhahahhhhhhhhhhhhhhhhhhhhhhhhhahahahahhhhhahahhhhhhhhhhhhhhhhhahahhhhhahahhhhhhhhhhhhhhhhhhhahahahahhhhhahahhhhhahahahahahahhhhhhhhhhhhhhhhhhhhhahahahahhhhhahahhhhhahahahahahahhhhhhhahahahahahahhhhhahahhahahhhhhhhhhhhhhhhhhhhhhhhhhahahahahhhhhahahhhhhahahhahahahahahhhhhahahhahahhhhhhhhhhhhhhhhhhhhhhhhhahahahahhhhhahahhhhhahahhahahahahahhhhhahahhahahhhhhhhhhhhhhhhhhhhhhhhhhahahahahhhhhahahhhhhahahhahahahahahhhhhahahhahahhhhhhhhhhhhhhhhhhhhhhhhhahahahahhhhhahahhhhhahahhahhahahhhhhhhhhhhhhhhhhhhhhhhhhahahahahhhhhahahhahahhahhhhhahahhahahhhhhhhhhhhhhhhhhhhhhhhhhahahahahhhhhahahhahahhahhahahhhhhhhhhhhhhhhhhhhhhhhhhahahahahhhhhahahhahahhhhhhhhhhhhhhhhhhhhhhhhhahahahahhhhhahahhahahhhhhhhhhhhhhhhhhhhhhhhhhhh",
                70, true,
                "OK", "Cancel"
        ).showWindow();
    }

    public static void shortTest() {
        new INoticeFrame(
                "Test",
                "This is a test",
                40,
                "OK", "Cancel"
        ).showWindow();
    }

    public static void chineseTest() {
        new INoticeFrame(
                "Test",
                "摸呀摸搓呀搓摸呀摸搓呀搓摸呀摸搓呀搓摸呀摸搓呀搓摸呀摸搓呀搓摸呀摸搓呀搓摸呀摸搓呀搓",
                60,
                "OK", "Cancel"
        ).showWindow();
    }

    public static void upperCaseTest() {
        new INoticeFrame(
                "Test",
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                40,
                "OK", "Cancel"
        ).showWindow();
    }
    public static void specialTest() {
        new INoticeFrame(
                "Test",
                "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiijjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff",
                40,
                "OK", "Cancel"
        ).showWindow();
    }

    public static void mixedTest() {
        new INoticeFrame(
                "Test",
                "我听不到我不想回家hufjonkihuifjdnhfudjnskhfuj哦豁完蛋了sohisjfenwhiuDJHEDSNJFUHndifsfhu",
                40,
                "OK", "Cancel"
        ).showWindow();
    }

    public static void fieldTest1() {
        new INoticeFrame(
                "错误",
                "程序遇到未知错误 Class.UnhandledException: No exception, just kidding :)",
                40,
                "OK", "Cancel"
        ).showWindow();
    }
    public static void fieldTest2() {
        new INoticeFrame(
                "错误",
                "用户名或密码错误",
                40,
                "OK", "Cancel"
        ).showWindow();
    }
    public static void fieldTest3() {
        INoticeFrame a = new INoticeFrame(
                "注意",
                "密码字段需要满足要求：\n长度不能少于8个字符\n长度不能多于16个字符\n只能包含英文字母和数字",
                40,
                "好的", "不好"
        );
        a.addActionListeners(
                e -> {
                    a.dispose();
                    System.exit(0);
                },
                e -> {
                    a.setText("滚");
                }
        );
        a.showWindow();
    }
}
