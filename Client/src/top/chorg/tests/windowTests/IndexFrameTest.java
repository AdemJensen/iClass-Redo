package top.chorg.tests.windowTests;

import top.chorg.kernel.api.UserInfo;
import top.chorg.window.index.IndexFrame;

public class IndexFrameTest {
    public static void main(String[] args) {
        new IndexFrame(new UserInfo(
                "Test user",
                "Test name",
                "123456789012",
                "test@nemo.none",
                "14423333333",
                1, 0
        )).showWindow();
    }
}
