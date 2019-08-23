package top.chorg.tests.windowTests.announce;

import top.chorg.tests.moduleTests.VariableTests;
import top.chorg.window.announce.AnnounceListFrame;

public class AnnounceListFrameTest {

    public static void main(String[] args) {
        VariableTests.simulateAuth(1);
        var a = new AnnounceListFrame(1);
        a.showWindow();
    }

}
