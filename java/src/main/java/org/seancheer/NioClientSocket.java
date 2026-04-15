package org.seancheer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class NioClientSocket {
    private final int port = 12306;
    private final String ip = "127.0.0.1";
    private final int BUF_SIZE = 1024;


    public static void main(String[] args) throws IOException {
        new NioClientSocket().startClient();
    }

    /**
     * NioClient
     */
    public void startClient() throws IOException {
        SocketChannel clientChannel = SocketChannel.open();
        clientChannel.configureBlocking(false);
        clientChannel.connect(new InetSocketAddress(ip, port));

        Selector selector = Selector.open();
        clientChannel.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ | SelectionKey.OP_WRITE);

        boolean read = false;
        boolean write = false;
        for (; ; ) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iter = keys.iterator();

            while (iter.hasNext()) {
                SelectionKey key = iter.next();
                iter.remove();
                if (!key.isValid()){
                    continue;
                }

                if (key.isConnectable()){
                    clientChannel.finishConnect();
                    clientChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                    continue;
                }

                if (key.isReadable() && !read) {
                    ByteBuffer buf = ByteBuffer.wrap(new byte[BUF_SIZE]);
                    clientChannel.read(buf);
                    System.out.println("From server:" + new String(buf.array(), StandardCharsets.UTF_8));
                    read = true;
                }

                if (key.isWritable() && !write) {
                    ByteBuffer buf = ByteBuffer.wrap("hello server, from client.".getBytes(StandardCharsets.UTF_8));
                    clientChannel.write(buf);
                    write = true;
                }
            }

            if (read && write) {
                clientChannel.close();
                selector.close();
                break;
            }
        }
    }
}
