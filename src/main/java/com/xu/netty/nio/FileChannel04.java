package com.xu.netty.nio;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * 源码就是纸老虎，并没有那么可怕
 * netty
 */
public class FileChannel04 {
    public static void main(String[] args) throws IOException {
        File file = new File("F:\\java_test\\netty\\风景.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream("F:\\java_test\\netty\\风景2.jpg");
        FileChannel readChannel = fileInputStream.getChannel();
        FileChannel writChannel = fileOutputStream.getChannel();

        writChannel.transferFrom(readChannel, 0, file.length());
        fileInputStream.close();
        fileOutputStream.close();
    }
}
