package top.chorg.tests.windowTests;

import top.chorg.tests.moduleTests.VariableTests;

import static top.chorg.kernel.Variable.masterFrame;

public class MasterFrameTest {
    public static void main(String[] args) {
        VariableTests.simulateAuth(1);
        masterFrame.refreshChatList();
        masterFrame.showWindow();
    }
}
