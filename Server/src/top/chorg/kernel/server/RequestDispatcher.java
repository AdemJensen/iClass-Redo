package top.chorg.kernel.server;

import com.google.gson.JsonParseException;
import top.chorg.kernel.foundation.Message;

import static top.chorg.kernel.Variable.*;

public class RequestDispatcher {

    /**
     * 本方法用于调度来自客户端的请求
     *
     * @param userId 发出请求的用户id
     * @param opsId 请求类型
     * @param obj 请求所使用的参数
     * @return 返回请求结果
     * @throws JsonParseException 处理可能出现的数据无效
     * 需要所有dispatch方法都抛出JsonParseException异常
     */
    private Object dispatch(int userId, int opsId, String obj) throws JsonParseException {
        switch(opsId) {
            case 1:
                return authDispatch.login(obj);
            case 2:
                return authDispatch.getRealName(obj);
            case 3:
                return authDispatch.register(obj);
            case 4:

            case 5:

            case 6:

            case 21:
                return fileDispatch.upload(userId, obj);
            case 22:
                return fileDispatch.download(obj);
            case 23:

            case 24:

            default:
                return "无效操作";
        }
    }

    public Message doDispatch(int userId, Message msg) {
        System.out.println("[DEBUG] Received: " + gson.toJson(msg));
        try {
            Object res = dispatch(userId, msg.getOpsId(), msg.getObj());
            if (res == null) return null;
            return new Message(msg.getOpsId(), res, msg.getToken());
        } catch (JsonParseException e) {
            return new Message(msg.getOpsId(), "服务数据格式错误", msg.getToken());
        }
    }

}
