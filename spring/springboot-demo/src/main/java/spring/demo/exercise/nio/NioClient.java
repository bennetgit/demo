package spring.demo.exercise.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.time.Instant;

/**
 * Created by facheng on 18-6-13.
 * <p>
 * 1.通道（Channel）：负责连接
 * java.nio.channels.Channel 接口：
 * |--SelectableChannel
 * |--SocketChannel
 * |--ServerSocketChannel
 * |--DatagramChannel
 * <p>
 * |--Pipe.SinkChannel
 * |--Pipe.SourceChannel
 * <p>
 * 2. 缓冲区（Buffer）：负责数据的存取
 * 3. 选择器（Selector）：是 SelectableChannel 的多路复用器。用于监控 SelectableChannel 的 IO 状况
 */
public class NioClient {

    public static void main(String[] args) throws IOException {

        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9999));
        ByteBuffer buf = ByteBuffer.allocate(1024);

        buf.put((Instant.now().getNano() + "   hello ").getBytes());
        buf.flip();
        socketChannel.write(buf);
        buf.clear();

        socketChannel.close();

    }
}
