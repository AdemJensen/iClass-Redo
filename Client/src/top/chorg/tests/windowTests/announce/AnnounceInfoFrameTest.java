package top.chorg.tests.windowTests.announce;

import top.chorg.kernel.api.announce.AnnouncementInfo;
import top.chorg.window.announce.AnnounceInfoFrame;

import java.util.Date;

public class AnnounceInfoFrameTest {

    public static void main(String[] args) {
        new AnnounceInfoFrame(new AnnouncementInfo(
                1, "Test Announce",
                "[\"content\",\"{\\\"color\\\":{\\\"value\\\":-16777216,\\\"falasha\\\":0.0}," +
                        "\\\"family\\\":\\\"Lucia Grade\\\",\\\"size\\\":13,\\\"isItalic\\\":false,\\\"isBold\\\":false," +
                        "\\\"isUnderline\\\":false,\\\"startOff\\\":0,\\\"len\\\":5,\\\"content\\\":\\\"Yahweh\\\"}\"," +
                        "\"icon\",\"0\",\"content\",\"{\\\"color\\\":{\\\"value\\\":-16777216,\\\"falasha\\\":0.0},\\" +
                        "\"family\\\":\\\"Lucia Grade\\\",\\\"size\\\":13,\\\"isItalic\\\":false,\\\"isBold\\\":fal" +
                        "se,\\\"isUnderline\\\":false,\\\"startOff\\\":6,\\\"len\\\":1,\\\"content\\\":\\\"Y\\\"}\",\"ic" +
                        "on\",\"0\",\"icon\",\"0\",\"icon\",\"0\",\"icon\",\"0\",\"icon\",\"0\",\"content\",\"{\\\"colo" +
                        "r\\\":{\\\"value\\\":-16777216,\\\"falasha\\\":0.0},\\\"family\\\":\\\"Lucia Grade\\\",\\\"si" +
                        "ze\\\":13,\\\"isItalic\\\":false,\\\"isBold\\\":false,\\\"isUnderline\\\":false,\\\"startO" +
                        "ff\\\":12,\\\"len\\\":23,\\\"content\\\":\\\"Yahweh r     tantalise\\\"}\"]\n",
                new Date(), new Date(),
                1, 2
        )).showWindow();
    }

}
