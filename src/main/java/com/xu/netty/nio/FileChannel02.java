package com.xu.netty.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * netty
 */
public class FileChannel02 {
    public static void main(String[] args) throws IOException {
        File file = new File("F:\\java_test\\netty\\1.txt");

        FileInputStream stream = new FileInputStream(file);

        FileChannel channel = stream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate((int) file.length());

        channel.read(buffer);

        System.out.println(new String(buffer.array()));
        stream.close();
    }
}
