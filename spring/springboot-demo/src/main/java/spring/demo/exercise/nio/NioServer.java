package spring.demo.exercise.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by facheng on 18-6-13.
 */
public class NioServer {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        ssChannel.configureBlocking(false);
//        ssChannel.bind(new InetSocketAddress("127.0.0.1", 9999));

        ServerSocket socket = ssChannel.socket();
        socket.bind(new InetSocketAddress("127.0.0.1", 9999));

        Selector selector = Selector.open();
        //将通道注册到选择器上,并且指定"监听接收事件"
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);
        //轮询式的获取选择器上已经"准备就绪"的事件

        while (selector.select() > 0) {

            System.out.println("start lookup.......");
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey sk = it.next();
                if (sk.isAcceptable()) {
                    SocketChannel sChannel = ((ServerSocketChannel) sk.channel()).accept();
                    if (sChannel == null) {
                        continue;
                    }
                    sChannel.configureBlocking(false);
                    //将该通道注册到选择器上
                    sChannel.register(selector, SelectionKey.OP_READ);
                }

                if (sk.isReadable()) {
                    //获取当前选择器上"读就绪"状态的通道
                    SocketChannel sChannel = (SocketChannel) sk.channel();
                    //读取数据
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int len;
                    while ((len = sChannel.read(buffer)) != -1) {
                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0, len));
                        buffer.clear();
                    }
                }
            }
        }


    }
}
