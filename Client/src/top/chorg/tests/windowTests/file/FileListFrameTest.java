package top.chorg.tests.windowTests.file;

import top.chorg.tests.moduleTests.VariableTests;
import top.chorg.window.file.FileListFrame;

public class FileListFrameTest {

    public static void main(String[] args) {
        VariableTests.simulateAuth(1);
        new FileListFrame(0, 1).showWindow();
    }

}
