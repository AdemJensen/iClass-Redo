package top.chorg.tests.windowTests;

import top.chorg.kernel.api.UserInfo;
import top.chorg.tests.moduleTests.VariableTests;
import top.chorg.window.index.MasterFrame;

import java.util.Date;

import static top.chorg.kernel.Variable.self;

public class MasterFrameTest {
    public static void main(String[] args) {
        VariableTests.simulateAuth(1);
        new MasterFrame(self).showWindow();
    }
}
