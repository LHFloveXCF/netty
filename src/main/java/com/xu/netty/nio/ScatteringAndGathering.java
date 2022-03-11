package com.xu.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * netty
 * Scattering:
 * Gathering:
 */
public class ScatteringAndGathering {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        InetSocketAddress inetAddress = new InetSocketAddress(5210);

        serverSocketChannel.socket().bind(inetAddress);

        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        SocketChannel accept = serverSocketChannel.accept();

        int messageLength = 8;

        while (true) {
            int readLength = 0;
            while (readLength < messageLength) {
                long read = accept.read(byteBuffers);
                readLength += read;
                System.out.println("byteRead" + readLength);
                Arrays.stream(byteBuffers).map(b -> String.format("position: %d, limit: %d", b.position(), b.limit())).forEach(System.out::println);
            }

            Arrays.asList(byteBuffers).forEach(Buffer::flip);

            int writeLength = 0;
            while (writeLength < messageLength) {
                long write = accept.write(byteBuffers);
                writeLength += write;
            }
            Arrays.asList(byteBuffers).forEach(Buffer::clear);

            System.out.println(String.format("byteRead: %d, writeLength: %d", readLength, writeLength));
        }

    }
}
