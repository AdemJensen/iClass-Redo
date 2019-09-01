package top.chorg.kernel.server.dispatch;

import com.google.gson.JsonParseException;
import top.chorg.kernel.api.auth.LoginRequest;
import top.chorg.kernel.api.auth.RegisterRequest;
import top.chorg.kernel.api.auth.UserInfo;

import static top.chorg.kernel.Variable.authData;
import static top.chorg.kernel.Variable.gson;

public class AuthDispatch {

    public Object login(String obj) throws JsonParseException {
        LoginRequest request = gson.fromJson(obj, LoginRequest.class);
        UserInfo info = authData.login(request.username, request.passwordHash);
        if (info == null) return "用户名或密码错误";
        return info;
    }

    public String[] getRealName(String obj) throws JsonParseException {
        int[] id = gson.fromJson(obj, int[].class);
        return authData.getRealName(id);
    }

    public Object register(String obj) throws JsonParseException {
        RegisterRequest request = gson.fromJson(obj, RegisterRequest.class);
        if (!authData.checkUsernameAvail(request.username)) return "用户名已存在";
        int res = authData.register(request);
        if (res == -1) return "服务器错误";
        return res;
    }

}
