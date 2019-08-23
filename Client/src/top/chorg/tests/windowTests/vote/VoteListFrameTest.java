package top.chorg.tests.windowTests.vote;

import top.chorg.tests.moduleTests.VariableTests;
import top.chorg.window.vote.VoteListFrame;

public class VoteListFrameTest {

    public static void main(String[] args) {
        VariableTests.simulateAuth(1);
        var a = new VoteListFrame(1);
        a.showWindow();
    }

}
