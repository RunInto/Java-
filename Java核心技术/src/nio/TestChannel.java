package nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author: ys
 * desc:
 * date: 2020/10/26
 */
public class TestChannel {
    public static void main(String[] args) throws Exception {
        channelTransform();
    }

    // 分散读取 将通道中的数据分散到多个缓冲区中
    // 聚集写入 将多个缓冲区中的数据聚集到通道中
    static void scatterAndGatter() {

    }

    // 通道通过transferFrom 或transferTo 传递
    static void channelTransform() throws Exception{
        FileChannel inChannel = FileChannel.open(Paths.get("/Users/ys/Documents/Java核心数据结构/Java核心技术/src/nio/1.png"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("/Users/ys/Documents/Java核心数据结构/Java核心技术/src/nio/4.png"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

//        inChannel.transferTo(0, inChannel.size(), outChannel);
        outChannel.transferFrom(inChannel, 0, inChannel.size());
    }


    // 操作直接内存（内存映射文件）
    static void fileCpDirect() throws Exception{
        FileChannel inChannel = FileChannel.open(Paths.get("/Users/ys/Documents/Java核心数据结构/Java核心技术/src/nio/1.png"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("/Users/ys/Documents/Java核心数据结构/Java核心技术/src/nio/3.png"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        // 内存映射文件
        MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        // 直接对缓冲区进行数据的读写
        byte[] dst = new byte[inMappedBuf.limit()];
        inMappedBuf.get(dst);
        outMappedBuf.put(dst);

        inChannel.close();
        outChannel.close();
    }



    // 利用通道复制文件（jvm 内存）
    static void fileCp() {
        FileInputStream in = null;
        FileOutputStream ou = null;

        FileChannel inChannel = null;
        FileChannel ouChannel = null;
        try {
            // 文件输入输出流
            in = new FileInputStream("/Users/ys/Documents/Java核心数据结构/Java核心技术/src/nio/1.png");
            ou = new FileOutputStream("/Users/ys/Documents/Java核心数据结构/Java核心技术/src/nio/2.png");

            // 通道
            inChannel = in.getChannel();
            ouChannel = ou.getChannel();

            //分配指定大小的缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);

            //将通道中的数据存入缓冲区
            while (inChannel.read(buf) != -1) {
                // 将position移动到文件首 读取position到limit之间的数据，意思就是切换到读模式
                buf.flip();
                ouChannel.write(buf);
                // 清空缓冲区，position和limit归位
                buf.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (ou != null) {
                try {
                    ou.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (ouChannel != null) {
                try {
                    ouChannel.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
