package nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * @author: ys
 * desc:
 * date: 2020/10/26
 */
public class TestCharSet {
    public static void main(String[] args) throws Exception{
        Charset gbk = Charset.forName("GBK");
        // 获取编码器
        CharsetEncoder encode = gbk.newEncoder();
        // 获取解码器
        CharsetDecoder decode = gbk.newDecoder();

        CharBuffer cBuf = CharBuffer.allocate(1024);
        cBuf.put("编码");
        cBuf.flip();

        // 编码
        ByteBuffer bBuf = encode.encode(cBuf);

        for (int i = 0; i < 4; i++) {
            System.out.println(bBuf.get(i));
        }

        // 解码
        CharBuffer buffer2 = decode.decode(bBuf);
        System.out.println(buffer2.toString());
    }
}
