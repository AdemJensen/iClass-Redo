package top.chorg.tests.moduleTests;

import java.util.Calendar;
import static top.chorg.support.TimeUtils.getTimeDurText;

public class VariableTests {
    public static void main(String[] args) {
        //Date a = new Date();
        Calendar b = Calendar.getInstance();
        b.set(2019, Calendar.AUGUST, 2);
        b.getTime();
        System.out.println(getTimeDurText(b.getTime()));
    }
}
