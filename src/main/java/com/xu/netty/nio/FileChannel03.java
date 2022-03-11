package com.xu.netty.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * netty
 */
public class FileChannel03 {
    public static void main(String[] args) throws IOException {
        File file = new File("F:\\java_test\\netty\\1.txt");

        FileInputStream stream = new FileInputStream(file);
        FileChannel fileChannel01 = stream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("F:\\java_test\\netty\\1.bak.txt");
        FileChannel fileChannel02 = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        while (true) {
            byteBuffer.clear();
            int read = fileChannel01.read(byteBuffer);
            byteBuffer.flip();
            fileChannel02.write(byteBuffer);
            if (read == -1) {
                break;
            }
        }
        stream.close();
        fileOutputStream.close();

    }
}
