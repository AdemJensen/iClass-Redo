package top.chorg.tests.windowTests.auth;

import top.chorg.kernel.api.auth.UserInfo;
import top.chorg.window.auth.UserProfileFrame;
import top.chorg.window.foundation.notice.IInformationFrame;

import java.util.Date;

public class UserProfileFrameTest {

    public static void main(String[] args) {
        new UserProfileFrame(
                new UserInfo(
                        "Test user",
                        new Date(),
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
