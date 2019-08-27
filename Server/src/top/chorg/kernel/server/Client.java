package top.chorg.kernel.server;

import top.chorg.kernel.foundation.Message;

import java.io.*;
import java.net.Socket;

import static top.chorg.kernel.Variable.requestDispatcher;

public class Client {

    private int id;

    private Socket socket;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;

    private Thread receiverThread;

    public Client(int id, Socket socket) {
        this.id = id;
        this.socket = socket;
        receiverThread = new Thread(() -> {
            try {
                while (true) {
                    String msg = bufferedReader.readLine();
                    if (msg == null) {
                        this.closeSocket();
                        break;
                    }
                    Message msgObj = new Message(msg);
                    Message result = requestDispatcher.dispatch(msgObj);
                    if (result != null) {
                        if (!send(result)) {
                            throw new IOException("Unable to send message " + result.prepareContent());
                        }
                    }
                }
            } catch (IOException e) {
                this.closeSocket();  // Socket连接中断
                System.out.printf("[user-%d] Error while listening IO: %s\n", id, e.getMessage());
            }
        });
    }

    public boolean send(Message msg) {
        try {
            if (socket.isConnected()) {
                synchronized (this) {      // 防止延迟和线程导致状态错误
                    printWriter.println(msg.prepareContent());
                    printWriter.flush();
                }
            } else {
                throw new RuntimeException("Logic error: Sending message before establishing connection");
            }
        } catch (Exception e) {
            System.out.printf("[user-%d] Error while sending msg: %s\n", id, e.getMessage());
            return false;
        }
        return true;
    }

    private void closeSocket() {
        try {
            socket.close();
            printWriter = null;
            bufferedReader = null;
        } catch (IOException ignored) { }
    }

    public void disconnect() {
        closeSocket();
        if (receiverThread.isAlive()) receiverThread.interrupt();
    }


}
