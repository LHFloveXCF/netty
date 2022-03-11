package com.xu.netty.nio;

import java.nio.ByteBuffer;

/**
 * netty
 */
public class NioBufferReadOnly {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);
        for (int i = 1; i < 64; i++) {
            buffer.put((byte) i);
        }
        buffer.flip();
        buffer.asReadOnlyBuffer();
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }

        // buffer.put((byte)1);
    }
}
