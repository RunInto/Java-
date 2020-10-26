package nio;

import java.nio.ByteBuffer;

/**
 * @author: ys
 * desc:
 * date: 2020/10/26
 */
public class TestBuffer {

    public static void main(String[] args) {
//        allockDirect();
        allocate();
    }
    // 分配到直接内存
    public static void allockDirect() {
        ByteBuffer buf = ByteBuffer.allocateDirect(1024);
        System.out.println(buf.isDirect());
    }


    // 分配到虚拟机内存中
    public static void allocate() {
        // 将缓冲区建立在jvm内存中
        ByteBuffer buf = ByteBuffer.allocate(1024);
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        buf.put("abcde".getBytes());

        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        // 切换到读模式
        buf.flip();

        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        byte[] dst = new byte[buf.limit()];
        buf.get(dst);
        System.out.println(new String(dst, 0, dst.length));

        // 可重复读数据
        buf.rewind();
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        // 清空缓冲区，但数据还在。处于'被遗忘状态'
        buf.clear();
    }
}
