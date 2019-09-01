package top.chorg.kernel.foundation;

import com.google.gson.JsonParseException;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

import static top.chorg.kernel.Variable.gson;
import static top.chorg.kernel.Variable.netDispatcher;

public class Network {

    protected Socket socket;      // 主Socket对象
    private String serverIp;
    private int port;
    protected PrintWriter printWriter;
    protected BufferedReader bufferedReader;
    private Thread listenerThread;  // 监听线程
    private ConcurrentHashMap<String, String> requestQueue;    // 等待反馈的请求列表

    public Network(String serverIp, int port) {
        this.serverIp = serverIp;
        this.port = port;
        requestQueue = new ConcurrentHashMap<>();
    }

    public boolean connect() {
        return connect(serverIp, port);
    }

    protected void listenerThreadAction() {
        try {
            while (true) {
                String msg = bufferedReader.readLine();
                if (msg == null) {
                    this.closeSocket();
                    break;
                }
                System.out.printf("[DEBUG-%s] Received: %s\n", this.getClass(), msg);
                Message msgObj = new Message(msg);
                if (requestQueue.containsKey(msgObj.getToken()) &&
                        requestQueue.get(msgObj.getToken()).equals("WAIT")) {
                    requestQueue.replace(msgObj.getToken(), msgObj.getObj());
                } else {
                    netDispatcher.dispatch(msgObj);
                }
            }
        } catch (IOException e) {
            this.closeSocket();  // Socket连接中断
        }
    }

    private boolean connect(String serverIp, int port) {          // 建立连接并开启监听
        try {
            socket = new Socket(serverIp, port);
            printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            listenerThread = new Thread(this::listenerThreadAction);
            listenerThread.start();
        } catch (IOException e1) {
            System.err.println("[ERR] " + e1.getMessage());
            this.disconnect();
            return false;
        }
        return true;
    }

    public <T> T send(Message msg, Class<T> typeOfReturn) throws Exception {     // 发送消息并获得回应
         if (socket != null && socket.isConnected()) {
             synchronized (this) {      // 防止延迟和线程导致状态错误
                 printWriter.println(msg.prepareContent());
                 requestQueue.put(msg.getToken(), "WAIT");
                 printWriter.flush();
             }
             while (requestQueue.get(msg.getToken()).equals("WAIT")) {
                 Thread.onSpinWait();
             }
             String result = requestQueue.get(msg.getToken());
             requestQueue.remove(msg.getToken());
             if (result.equals("ERROR")) return null;       // 留着可以给服务端返回时使用，系统错误
             try {
                 return gson.fromJson(result, typeOfReturn);
             } catch (JsonParseException e) {
                 throw new Exception(result);
             }
         } else {
             throw new RuntimeException("Logic error: Sending message before establishing connection");
         }
    }

    public PrintWriter getPrintWriter() {
        return printWriter;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    /*
     * 自动建立连接，发送消息并获得回应
     * 与send不同点，一个是自动建立连接，另一个是发送后自动断开连接。
     * 如果连接已建立，则与send相同，不会在发送完成后断开连接。
     * 不建议在正式建立长连接以前多次使用
     */
    public <T> T post(String serverIp, int port, Message msg, Class<T> typeOfReturn) throws Exception {
        if (socket != null && socket.isConnected()) return send(msg, typeOfReturn);
        else synchronized (this) {
            if (!this.connect(serverIp, port)) return null;
            T result = send(msg, typeOfReturn);
            this.disconnect();
            return result;
        }
    }

    public <T> T post(Message msg, Class<T> typeOfReturn) throws Exception {
        return post(serverIp, port, msg, typeOfReturn);
    }

    private void closeSocket() {
        try {
            if (socket != null) {
                socket.close();
                bufferedReader.close();
                printWriter.close();
            }
            socket = null;
        } catch (IOException ignored) { }
    }

    public void disconnect() {
        closeSocket();
        if (listenerThread != null && listenerThread.isAlive()) listenerThread.interrupt();
    }

}
