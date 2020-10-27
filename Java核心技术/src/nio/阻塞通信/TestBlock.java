package nio.阻塞通信;



import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author: ys
 * desc: 阻塞式IO
 * date: 2020/10/27
 */
public class TestBlock {


    @Test
    public void client() throws Exception {
        // 获取与服务器的通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
        // 获取文件传输通道
        FileChannel inChannel = FileChannel.open(Paths.get("/Users/ys/Documents/Java核心数据结构/Java核心技术/src/nio/1.png"), StandardOpenOption.READ);
        // 分配指定大小缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
        //读取本地文件，并发送到服务器
        while (inChannel.read(buf) != -1) {
            buf.flip();
            socketChannel.write(buf);
            buf.clear();
        }

        socketChannel.close();
        inChannel.close();
        System.out.println("client end");
    }

    @Test
    public void server() throws Exception {
        // 获取服务端通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //绑定端口号
        serverSocketChannel.bind(new InetSocketAddress(9898));
        //获取客户端连接的通道
        SocketChannel socketChannel = serverSocketChannel.accept();
        //接收客户端的数据，并保存到本地
        ByteBuffer buf = ByteBuffer.allocate(1024);
        FileChannel outChannel = FileChannel.open(Paths.get("/Users/ys/Documents/Java核心数据结构/Java核心技术/src/nio/2.png"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        while (socketChannel.read(buf) != -1) {
            buf.flip();
            outChannel.write(buf);
            buf.clear();
        }

        socketChannel.close();
        outChannel.close();
        serverSocketChannel.close();
        System.out.println("server end");
    }
}
