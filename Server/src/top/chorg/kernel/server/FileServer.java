package top.chorg.kernel.server;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

import static top.chorg.kernel.Variable.*;

public class FileServer extends ServerBase {

    private ConcurrentHashMap<String, String> queueList = new ConcurrentHashMap<>();     // server addr
    private ConcurrentHashMap<String, Integer> registerList = new ConcurrentHashMap<>(); // hash code

    public FileServer() {
        super(8889);
    }

    @Override
    public void onNewConnection(Socket client, PrintWriter writer, BufferedReader reader) {
        try {
            String code = reader.readLine();
            if (queueList.containsKey(code)) {
                String fileName = queueList.get(code);
                queueList.remove(code);
                String method = reader.readLine();
                long size;
                switch (method) {
                    case "upload":
                        size = upload(client, fileName);
                        if (registerList.containsKey(code)) {
                            fileData.completeRegFile(registerList.get(code), size);
                        }
                        break;
                    case "download":
                        File file = new File(data(fileName));
                        writer.println(file.length());
                        writer.flush();
                        download(client, fileName);
                        break;
                    default:
                        System.out.println("[File] Invalid request by " + client.hashCode());
                        client.close();
                }
            } else {
                System.out.println("[File] Invalid connection request by " + client.hashCode());
                client.close();
            }
        } catch(IOException e) {
            System.out.println("[File] Connection closed unexpectedly by " + client.hashCode());
        }
    }

    private long upload(Socket client, String fileName) throws IOException {
        var fo = new FileOutputStream(data(fileName));
        var bytes = new byte[1024];
        long size = 0;
        for (int length; (length = client.getInputStream().read(bytes)) != -1; ) {
            fo.write(bytes, 0, length);
            size += length;
        }
        fo.close();
        client.close();
        System.out.printf("[File] %s has been uploaded\n", fileName);
        return size;
    }

    private void download(Socket client, String fileName) throws IOException {
        var fin = new FileInputStream(data(fileName));
        var bytes = new byte[1024];
        var os = client.getOutputStream();
        for (int length; (length = fin.read(bytes)) != -1; ) {
            os.write(bytes, 0, length);
        }
        client.close();
        fin.close();
        System.out.printf("[File] %s has been downloaded\n", fileName);
    }

    /**
     * 注册一个基础服务需要用到的文件，例如头像、聊天图片
     *
     * @param code 上传文件的密码
     * @param fileName 文件在服务器上的名字
     */
    public void register(String code, String fileName) {
        queueList.put(code, fileName);
    }

    /**
     * 将文件注册为属于某个列表的文件
     *
     * @param code 上传文件的密码
     * @param owner 文件的所有者
     * @param storageType 指定上传文件目标列表对象类型，
     *             0 = 班级列表
     *             1 = 用户列表
     * @param targetId 列表编号
     *                 若type = 0，则targetId为班级编号
     *                 若type = 1，则targetId为目标用户编号
     * @param name 文件上传前原本的名字
     * @param hash 文件的hash值
     */
    public void register(String code, int owner, int targetId, int storageType, String name, String hash) {
        register(code, getServerFileName(hash));
        registerList.put(code, fileData.registerFile(owner, targetId, storageType, name, hash));
    }

}
