package top.chorg.tests.moduleTests;

import top.chorg.kernel.api.UserInfo;

import java.util.Calendar;
import java.util.Date;

import static top.chorg.kernel.Variable.self;
import static top.chorg.support.TimeUtils.getTimeDurText;

public class VariableTests {
    public static void main(String[] args) {
        //Date a = new Date();
        Calendar b = Calendar.getInstance();
        b.set(2019, Calendar.AUGUST, 2);
        b.getTime();
        System.out.println(getTimeDurText(b.getTime()));
    }

    public static void simulateAuth(int id) {
        self = new UserInfo(
                "simulation" + id, new Date(), "测试账号", "000000000000",
                "simulation@test.nemo", "15555555555", "1000000000", id, 1
        );
    }

}
