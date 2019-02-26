import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * author: dulei
 * date: 18-9-17
 * desc:
 */
public class NIOFileDemo {
    @Test
    public void testNio(){
        try {
            RandomAccessFile rdf=new RandomAccessFile("E:\\nio\\niotest.txt","rw");
            //利用channel中的FileChannel来实现文件的读取
            FileChannel inChannel=  rdf.getChannel();
            //设置缓冲区容量为10
            ByteBuffer buf=  ByteBuffer.allocate(10);
            //从通道中读取数据到缓冲区，返回读取的字节数量
            int byteRead=inChannel.read(buf);
            //数量为-1表示读取完毕。
            while (byteRead!=-1){
                //切换模式为读模式，其实就是把postion位置设置为0，可以从0开始读取
                buf.flip();
                //如果缓冲区还有数据
                while (buf.hasRemaining()){
                    //输出一个字符
                    System.out.print((char) buf.get());
                }
                //数据读完后清空缓冲区
                buf.clear();
                //继续把通道内剩余数据写入缓冲区
                byteRead = inChannel.read(buf);
            }
            //关闭通道
            rdf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
