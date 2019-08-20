package top.chorg.window.auth;

import top.chorg.window.foundation.notice.IInformationFrame;

public class ForgotPasswordFrame extends IInformationFrame {

    public ForgotPasswordFrame() {
        super(
                "找回密码 - iClass", "抱歉，该功能暂未开放", e -> {
                    new LoginFrame().showWindow();
                }
        );
    }
}
