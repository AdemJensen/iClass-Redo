package top.chorg.kernel.server;

import top.chorg.kernel.foundation.Message;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import static top.chorg.kernel.Variable.*;

public class MasterServer extends ServerBase {

    public MasterServer() {
        super(8888);
    }

    @Override
    public void onNewConnection(Socket client, PrintWriter writer, BufferedReader reader) {
        Client temp = new Client(client, writer, reader);
        Timer timer = new Timer(false);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!clients.contains(temp) && temp.isConnected()) {
                    temp.send(new Message(0, "数据传输超时", "0"));
                    temp.disconnect();
                    System.out.println("[Master] Client Unidentified-" + client.hashCode() +
                            " stayed unconnected for too long, rejected automatically.");
                }
            }
        }, 3000);
    }
}
