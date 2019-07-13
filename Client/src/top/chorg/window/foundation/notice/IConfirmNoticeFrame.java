package top.chorg.window.foundation.notice;

import top.chorg.window.foundation.INoticeFrame;

import java.awt.event.ActionListener;

public class IConfirmNoticeFrame extends INoticeFrame {

    public IConfirmNoticeFrame(String actionName, ActionListener confirmListener, ActionListener cancelListener) {
        super("提示", "您确定要" + actionName + "吗？", "确定", "取消");
        ActionListener confL = e -> {       // 完成执行后关闭对话框
            confirmListener.actionPerformed(e);
            IConfirmNoticeFrame.this.dispose();
        };
        ActionListener clrL = e -> {        // 完成执行后关闭对话框
            cancelListener.actionPerformed(e);
            IConfirmNoticeFrame.this.dispose();
        };
        this.addActionListeners(confL, clrL);
    }

    public IConfirmNoticeFrame(String actionName, ActionListener confirmListener) {
        this(actionName, confirmListener, e -> { });
    }

}
