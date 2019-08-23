package top.chorg.tests.windowTests;

import top.chorg.window.foundation.IPagedListFrame;

public class IPagedListFrameTest {

    public static void main(String[] args) {
        var a = new IPagedListFrame(480, 680, "测试");
        a.setTotalPage(4);
        a.setPageNum(1);
        a.showWindow();
    }

}
