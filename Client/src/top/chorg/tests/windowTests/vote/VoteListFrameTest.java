package top.chorg.tests.windowTests.vote;

import top.chorg.tests.moduleTests.VariableTests;
import top.chorg.window.foundation.notice.IInformationFrame;
import top.chorg.window.vote.VoteListFrame;

public class VoteListFrameTest {

    public static void main(String[] args) {
        VariableTests.simulateAuth(1);
        try {
            new VoteListFrame(1).showWindow();
        } catch (Exception e1) {
            new IInformationFrame("错误", "数据加载失败！").showWindow();
        }
    }

}
