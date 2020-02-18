package com.zsj.nio.ServerSocketChannel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ChannelAccept {

    public static final String GREETING = "Hello I must be going.\r\n";


    public static void main(String[] argv)
            throws Exception {
        int port = 1234; // default
        if (argv.length > 0) {
            port = Integer.parseInt(argv[0]);
        }
        ByteBuffer buffer = ByteBuffer.wrap(GREETING.getBytes());
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(port));
        ssc.configureBlocking(false);
        while (true) {
            System.out.println("Waiting for connections");
            SocketChannel sc = ssc.accept();
            if (sc == null) {
// no connections, snooze a while
                Thread.sleep(2000);
            } else {
                sc.configureBlocking(false);
                ByteBuffer allocate = ByteBuffer.allocateDirect (16 * 1024);
                while(sc.read(allocate)>0){
                    allocate.flip();
                    while (buffer.hasRemaining( )) {
                        byte b = buffer.get();
                        System.out.println(b);
                    }
                    allocate.clear();
                }

                System.out.println("Incoming connection from: "
                        + sc.socket().getRemoteSocketAddress());
                buffer.rewind();
                sc.write(buffer);
                sc.close();
            }
        }
    }
}
