package top.chorg.window.foundation.notice;

import java.awt.event.ActionListener;

public class IInformationFrame extends INoticeFrame {

    public IInformationFrame(String title, String content, boolean scrollable, ActionListener confirmListener) {
        super(title, content, scrollable, "确定");
        ActionListener confL = e -> {       // 完成执行后关闭对话框
            confirmListener.actionPerformed(e);
            IInformationFrame.this.dispose();
        };
        this.addActionListeners(confL);
    }

    public IInformationFrame(String title, String content, ActionListener confirmListener) {
        this(title, content, false, confirmListener);
    }

    public IInformationFrame(String title, String content, boolean scrollable) {
        this(title, content, scrollable, e -> { });
    }

    public IInformationFrame(String title, String content) {
        this(title, content, e -> { });
    }

}
