package top.chorg;

import java.sql.DriverManager;
import java.sql.SQLException;

import static top.chorg.kernel.Variable.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to iClass Server.");
        System.out.print("[SYS] Energizing database connection...");
        try {
            database = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/iClass",
                    "root",
                    "20000214"
            );
        } catch (SQLException e) {
            System.out.println("failed");
            System.err.println("[ERR] " + e.getMessage());
            System.out.println("[SYS] System exiting with error.");
            return;
        }
        System.out.println("success");
        System.out.print("[SYS] Energizing master server...");
        if (!masterServer.startServer()) {
            System.out.println("failed");
            System.out.println("[SYS] System exiting with error.");
            return;
        } else System.out.println("success");
        System.out.print("[SYS] Energizing file server...");
        if (!fileServer.startServer()) {
            System.out.println("failed");
            System.out.println("[SYS] System exiting with error.");
            return;
        } else System.out.println("success");
        System.out.println("[SYS] Initialization complete, server status: RUNNING");
        while (masterServer.isServerRunning() && fileServer.isServerRunning()) {
            Thread.onSpinWait();
        }
        masterServer.stopServer();
        fileServer.stopServer();
        clients.forEach((integer, client) -> client.disconnect());
        System.out.println("[SYS] Server status: STOPPED");
    }
}
