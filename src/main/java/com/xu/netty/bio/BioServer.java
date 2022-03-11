package com.xu.netty.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * netty
 */
public class BioServer {
    public static void main(String[] args) throws IOException {
        ExecutorService pool = Executors.newCachedThreadPool();
        ServerSocket socket = new ServerSocket(5210);
        System.out.println("服务端启动了！");
        while (true) {
            System.out.println("wait--------");
            final Socket accept = socket.accept();
            System.out.println("连接一个客户端");
            pool.execute(new Runnable() {
                public void run() {
                    handler(accept);
                }
            });
        }
    }

    private static void handler(Socket socket) {
        try {
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();
            while (true) {
                System.out.println("read---------");
                int read = inputStream.read(bytes);
                if (read != -1) {
                    System.out.println(new String(bytes, 0 , read));
                } else {
                    break;
                }
            }

        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException ex) {
            }
        }
    }
}
