package top.chorg.kernel.server;

import com.google.gson.JsonParseException;
import top.chorg.kernel.api.auth.UserInfo;
import top.chorg.kernel.foundation.Message;

import java.io.*;
import java.net.Socket;

import static top.chorg.kernel.Variable.*;

public class Client {

    private int id = -1;

    private Socket socket;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;

    private Thread receiverThread;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIdentified() {
        return id > 0;
    }

    public Client(Socket socket, PrintWriter writer, BufferedReader reader) {
        this.socket = socket;
        this.printWriter = writer;
        this.bufferedReader = reader;
        receiverThread = new Thread(() -> {
            try {
                while (true) {
                    String msg = bufferedReader.readLine();
                    if (msg == null) {
                        this.closeSocket();
                        break;
                    }
                    Message msgObj = new Message(msg);
                    if (id == -1) {
                        if (msgObj.getOpsId() == 1 || msgObj.getOpsId() == 3) {
                            Message result = requestDispatcher.doDispatch(id, msgObj);
                            if (result != null) {
                                if (result.getOpsId() == 1) {
                                    UserInfo info;
                                    try {
                                        info = gson.fromJson(result.getObj(), UserInfo.class);
                                        if (clients.containsKey(info.id)) {
                                            result = new Message(1, "用户已在线", result.getToken());
                                        } else {
                                            this.setId(info.id);
                                            clients.put(info.id, Client.this);
                                            System.out.printf(
                                                    "[Master] New client on the list: soc-%d -> u-%d\n",
                                                    socket.hashCode(), info.id
                                            );
                                        }
                                    } catch (JsonParseException ignored) { }
                                }
                                if (!send(result)) {
                                    throw new IOException("Unable to send message " + result.prepareContent());
                                }
                            }
                        } else {
                            throw new IOException("[Master] Invalid request by " + socket.hashCode());
                        }
                    } else {
                        Message result = requestDispatcher.doDispatch(id, msgObj);
                        if (result != null && !send(result)) {
                            throw new IOException("Unable to send message " + result.prepareContent());
                        }
                    }
                }
            } catch (IOException e) {
                this.closeSocket();  // Socket连接中断
                if (isIdentified()) {
                    System.out.printf("[user-%d] Connection lost: %s\n", id, e.getMessage());
                } else {
                    System.out.printf("[Unidentified-%d] Connection lost: %s\n",
                            socket.hashCode(), e.getMessage());
                }
            }
        });
        receiverThread.start();
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
            if (isIdentified()) {
                System.out.printf("[user-%d] Error while sending msg: %s\n", id, e.getMessage());
            } else {
                System.out.printf("[Unidentified-%d] Error while sending msg: %s\n",
                        socket.hashCode(), e.getMessage());
            }
            return false;
        }
        return true;
    }

    private void closeSocket() {
        try {
            socket.close();
            bufferedReader.close();
            printWriter.close();
            if (id > 0) {
                clients.remove(id);
                if (isIdentified()) {
                    System.out.printf("[user-%d] Removed from the client list.\n", id);
                }
            } else {
                System.out.printf("[Unidentified-%d] Connection closed.\n", socket.hashCode());
            }
        } catch (IOException ignored) { }
    }

    public void disconnect() {
        closeSocket();
        if (receiverThread.isAlive()) receiverThread.interrupt();
    }

    public boolean isConnected() {
        return this.socket.isConnected() && !this.socket.isClosed();
    }

}
