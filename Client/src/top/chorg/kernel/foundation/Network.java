package top.chorg.kernel.foundation;

import com.google.gson.JsonParseException;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

import static top.chorg.kernel.Variable.gson;
import static top.chorg.kernel.Variable.netDispatcher;

public class Network {

    private Socket socket;      // 主Socket对象
    private String serverIp;
    private int port;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    private Thread listenerThread;  // 监听线程
    private ConcurrentHashMap<String, String> requestQueue;    // 等待反馈的请求列表

    public Network(String serverIp, int port) {
        this.socket = new Socket();
        this.serverIp = serverIp;
        this.port = port;
        requestQueue = new ConcurrentHashMap<>();
    }

    public boolean connect() {
        return connect(serverIp, port);
    }

    private boolean connect(String serverIp, int port) {          // 建立连接并开启监听
        try {
            socket.connect(new InetSocketAddress(serverIp, port));
            printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            listenerThread = new Thread(() -> {
                try {
                    while (true) {
                        String msg = bufferedReader.readLine();
                        if (msg == null) {
                            this.closeSocket();
                        }
                        Message msgObj = new Message(msg);
                        if (requestQueue.contains(msgObj.getToken()) &&
                                requestQueue.get(msgObj.getToken()).equals("WAIT")) {
                            requestQueue.replace(msgObj.getToken(), msgObj.getObj());
                        } else {
                            netDispatcher.dispatch(msgObj);
                        }
                    }
                } catch (IOException e) {
                    this.closeSocket();  // Socket连接中断
                }
            });
        } catch (IOException e1) {
            this.disconnect();
            return false;
        }
        return true;
    }

    public <T> T send(Message msg, Class<T> typeOfReturn) {     // 发送消息并获得回应
         if (socket.isConnected()) {
             synchronized (this) {      // 防止延迟和线程导致状态错误
                 printWriter.println(msg.prepareContent());
                 requestQueue.put(msg.getToken(), "WAIT");
                 printWriter.flush();
             }
             while (requestQueue.get(msg.getToken()).equals("WAIT")) {
                 Thread.onSpinWait();
             }
             requestQueue.remove(msg.getToken());
             String result = requestQueue.get(msg.getToken());
             if (result.equals("ERROR")) return null;       // 留着可以给服务端返回时使用，系统错误
             try {
                 return gson.fromJson(result, typeOfReturn);
             } catch (JsonParseException e) {
                 return null;
             }

         } else {
             throw new RuntimeException("Logic error: Sending message before establishing connection");
         }
    }

    /*
     * 自动建立连接，发送消息并获得回应
     * 与send不同点，一个是自动建立连接，另一个是发送后自动断开连接。
     * 如果连接已建立，则与send相同，不会在发送完成后断开连接。
     * 不建议在正式建立长连接以前多次使用
     */
    public <T> T post(String serverIp, int port, Message msg, Class<T> typeOfReturn) {
        if (socket.isConnected()) return send(msg, typeOfReturn);
        else synchronized (this) {
            if (!this.connect(serverIp, port)) return null;
            T result = send(msg, typeOfReturn);
            this.disconnect();
            return result;
        }
    }

    public <T> T post(Message msg, Class<T> typeOfReturn) {
        return post(serverIp, port, msg, typeOfReturn);
    }

    public void closeSocket() {
        try {
            socket.close();
            printWriter = null;
            bufferedReader = null;
        } catch (IOException ignored) { }
    }

    public void disconnect() {
        closeSocket();
        if (listenerThread.isAlive()) listenerThread.interrupt();
    }

}
