package SimpleCsv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * author: dulei
 * date: 18-4-28
 * desc: 写入csv
 */
public class TestWrite {
    public static void main(String[] args) {
        File csv = new File("/home/dulei/tmp/WriteTest.csv");
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csv,true));//追加
            // 添加新的数据行
            writer.write("\"张三\"" + "," + "\"2000\"" + "," + "\"2004\"");
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
