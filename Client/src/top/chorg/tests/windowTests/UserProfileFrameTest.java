package top.chorg.tests.windowTests;

import top.chorg.kernel.api.UserInfo;
import top.chorg.window.auth.UserProfileFrame;
import top.chorg.window.foundation.notice.IInformationFrame;

public class UserProfileFrameTest {

    public static void main(String[] args) {
        new UserProfileFrame(
                new UserInfo(
                "Test user",
                "Test name",
                "123456789012",
                "test@nemo.none",
                "14423333333",
                "1000000000",
                1, 0
                ),
                e -> new IInformationFrame("SUCCESS", "SUCCESS").showWindow()
        ).showWindow();
    }

}
