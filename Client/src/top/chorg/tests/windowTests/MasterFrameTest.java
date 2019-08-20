package top.chorg.tests.windowTests;

import top.chorg.kernel.api.UserInfo;
import top.chorg.window.index.MasterFrame;

public class MasterFrameTest {
    public static void main(String[] args) {
        new MasterFrame(new UserInfo(
                "Test user",
                "Test name",
                "123456789012",
                "test@nemo.none",
                "14423333333",
                "1000000000",
                1, 0
        )).showWindow();
    }
}
