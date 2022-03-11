package com.xu.netty.nio;

import java.nio.IntBuffer;

/**
 * netty
 */
public class NIOBuffer {
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(5);

        for (int i = 0; i < 5; i++) {
            intBuffer.put(i);
        }
        IntBuffer flip = (IntBuffer) intBuffer.flip();
        while (flip.hasRemaining()) {
            System.out.println(flip.get());
        }
    }
}
