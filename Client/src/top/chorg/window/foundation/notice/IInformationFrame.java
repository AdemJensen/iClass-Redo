package top.chorg.window.foundation.notice;

import top.chorg.window.foundation.INoticeFrame;

import java.awt.event.ActionListener;

public class IInformationFrame extends INoticeFrame {

    public IInformationFrame(String title, String content, ActionListener confirmListener) {
        super(title, content, "确定");
        ActionListener confL = e -> {       // 完成执行后关闭对话框
            confirmListener.actionPerformed(e);
            IInformationFrame.this.dispose();
        };
        this.addActionListeners(confL);
    }

    public IInformationFrame(String title, String content) {
        this(title, content, e -> { });
    }

}
