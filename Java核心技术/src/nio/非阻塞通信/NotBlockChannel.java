package nio.非阻塞通信;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;

/**
 * @author: ys
 * desc: 非阻塞通信
 * date: 2020/10/27
 */
public class NotBlockChannel {
    @Test
    public void client() throws Exception {
        // 建立与服务端通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9798));
        // 切换非租塞
        socketChannel.configureBlocking(false);
        // 建立缓冲区
        ByteBuffer byteBuf = ByteBuffer.allocate(1024);
        // 建立文件的传输通道
        FileChannel fileChannel = FileChannel.open(Paths.get("/Users/ys/Documents/Java核心数据结构/Java核心技术/src/nio/1.png"), StandardOpenOption.READ);
        // 向服务端发送数据
        while (fileChannel.read(byteBuf) != -1) {
            byteBuf.flip();
            socketChannel.write(byteBuf);
            byteBuf.clear();
        }
        // 关闭通道
        socketChannel.close();
        fileChannel.close();
    }

    @Test
    public void server() throws Exception {
        // 建立与客户端通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9798));
        serverSocketChannel.configureBlocking(false);
        SocketChannel socket = serverSocketChannel.accept();
        ByteBuffer bBuf = ByteBuffer.allocate(1024);
        FileChannel fileChannel = FileChannel.open(Paths.get("/Users/ys/Documents/Java核心数据结构/Java核心技术/src/nio/4.png"), StandardOpenOption.CREATE, StandardOpenOption.WRITE);

        // 获取选择器
        Selector selector = Selector.open();
        // 将通道注册到选择器上， 并且制定监听接收连接事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 轮询的获取选择器上已经准备就绪的事件
        while (selector.select() > 0) {
            // 获取当前选择器中所有注册的选择键（已就绪的监听事件）
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                // 获取准备就绪的事件
                SelectionKey sk = it.next();
                // 判断具体是什么事件准备就绪
                if (sk.isAcceptable()) {
                    // 获取客户端连接
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    // 切换到非阻塞模式
                    socketChannel.configureBlocking(false);
                    // 将该通道注册到选择器上
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (sk.isReadable()) {
                    // 获取当前选择器上读就绪状态的通道
                    SocketChannel sChannel = (SocketChannel) sk.channel();
                    // 读取数据
                    ByteBuffer buf = ByteBuffer.allocate(1024);
                    int len = 0;
                    while ((len = sChannel.read(buf)) > 0) {
                        buf.flip();
                        fileChannel.write(buf);
                        buf.clear();
                    }
                    sChannel.close();
                }
                // 取消选择键
                it.remove();
            }
        }
    }
}
