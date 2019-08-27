package top.chorg.kernel.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class ServerBase {

    private boolean isRunning = false;
    private Thread listenerThread;
    private ServerSocket serverSocket;
    private int port;

    public ServerBase(int port) {
        this.port = port;
    }

    public boolean startServer() {
        if (isRunning) return false;
        isRunning = true;
        listenerThread = new Thread(() -> {
            try {
                serverSocket = new ServerSocket(port);// 监听客户端的连接
                while (isRunning) {
                    Socket clientSocket = serverSocket.accept(); // 阻塞
                    PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    onNewConnection(clientSocket, printWriter, bufferedReader);
                }
            } catch (IOException e) {
                System.out.println("[Master] The master server has stopped due to error:" + e.getMessage());
                isRunning = false;
            }
        });
        return true;
    }

    public abstract void onNewConnection(Socket client, PrintWriter writer, BufferedReader reader);

    public boolean isServerRunning() {
        return isRunning;
    }

    public void stopServer() {
        isRunning = false;
        listenerThread.interrupt();
    }

}
