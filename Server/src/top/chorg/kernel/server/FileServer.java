package top.chorg.kernel.server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class FileServer extends ServerBase {


    public FileServer() {
        super(8889);
    }

    @Override
    public void onNewConnection(Socket client, PrintWriter writer, BufferedReader reader) {

    }
}
