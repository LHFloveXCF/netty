package com.xu.netty.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * netty
 */
public class FileChannel01 {
    public static void main(String[] args) throws IOException {
        String str = "hello world, i love you";
        // 获取输出流
        FileOutputStream fileOutputStream = new FileOutputStream("F:\\java_test\\netty\\1.txt");
        FileChannel channel = fileOutputStream.getChannel();

        // 内容写入buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(str.getBytes());

        // buffer反转
        buffer.flip();
        // 写入文件
        channel.write(buffer);
        // 关闭文件流
        fileOutputStream.close();
    }
}
