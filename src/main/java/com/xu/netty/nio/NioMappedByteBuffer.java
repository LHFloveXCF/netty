package com.xu.netty.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * netty
 * MappedByteBuffer可以让文件直接再内存（堆外内存）中修改，不需要拷贝
 */
public class NioMappedByteBuffer {
    public static void main(String[] args) throws IOException {
        RandomAccessFile accessFile = new RandomAccessFile("F:\\java_test\\netty\\1.txt", "rw");
        FileChannel channel = accessFile.getChannel();
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        map.put(0, (byte) 'W');

        accessFile.close();
        System.out.println("成功~~~~");

    }
}
