package top.chorg.kernel.server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MasterServer extends ServerBase {

    public MasterServer() {
        super(8888);
    }

    @Override
    public void onNewConnection(Socket client, PrintWriter writer, BufferedReader reader) {

    }
}
