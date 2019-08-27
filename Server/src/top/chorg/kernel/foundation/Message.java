package top.chorg.kernel.foundation;

import com.google.gson.JsonParseException;
import com.google.gson.internal.Primitives;
import top.chorg.support.MD5;

import static top.chorg.kernel.Variable.gson;

public class Message {
    private int opsId;
    private String obj;
    private String token;

    private static int messageNum = 0;

    public Message(int opsId, Object obj, String token) {
        this.opsId = opsId;
        this.obj = gson.toJson(obj);
        this.token = token;
    }

    public Message(int opsId, Object obj) {
        this.opsId = opsId;
        this.obj = gson.toJson(obj);
        this.token = generateToken();
    }

    public Message(Message alter) {
        this.opsId = alter.opsId;
        this.obj = alter.obj;
        this.token = alter.token;
    }

    public Message(String incomingMessage) throws JsonParseException {
        this(gson.fromJson(incomingMessage, Message.class));
    }

    public String getToken() {
        return token;
    }

    public int getOpsId() {
        return opsId;
    }

    public <T> T getObj(Class<T> typeOfVal) {
        return Primitives.wrap(typeOfVal).cast(obj);
    }

    public String getObj() {
        return obj;
    }

    public String prepareContent() {
        return gson.toJson(this);
    }

    private String generateToken() {
        messageNum = (messageNum + 1) % 2147480000;
        return MD5.encode(Integer.toString(messageNum));
    }

}
