package top.chorg.tests;

import top.chorg.kernel.api.auth.LoginRequest;

import static top.chorg.kernel.Variable.gson;

public class JsonTest {

    public static void main(String[] args) {
        Object obg = new LoginRequest("1", "@");
        System.out.println(obg.getClass());
        System.out.println(gson.toJson(obg));
    }

}
