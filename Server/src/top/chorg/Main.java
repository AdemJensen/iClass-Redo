package top.chorg;

import top.chorg.kernel.server.Client;

import static top.chorg.kernel.Variable.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to iClass Server.");
        System.out.print("[SYS] Energizing master server...");
        if (!masterServer.startServer()) {
            System.out.println("failed");
            System.out.println("[SYS] System exiting with error.");
        } else System.out.println("success");
        System.out.print("[SYS] Energizing file server...");
        if (!fileServer.startServer()) {
            System.out.println("failed");
            System.out.println("[SYS] System exiting with error.");
        } else System.out.println("success");
        System.out.println("[SYS] Initialization complete, server status: RUNNING");
        while (masterServer.isServerRunning() && fileServer.isServerRunning()) {
            Thread.onSpinWait();
        }
        masterServer.stopServer();
        fileServer.stopServer();
        clients.forEach(Client::disconnect);
        System.out.println("[SYS] Server status: STOPPED");
    }
}
