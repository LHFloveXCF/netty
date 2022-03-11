package com.xu.netty.nio;

import java.nio.ByteBuffer;

/**
 * netty
 */
public class NioBufferPutAndGet {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);

        buffer.putInt(1);
        buffer.putChar('我');
        buffer.putLong(20);
        buffer.putShort((short) 39);

        buffer.flip();

        System.out.println(buffer.getInt());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getShort());
    }
}
