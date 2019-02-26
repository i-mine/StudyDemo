package SimpleCsv;

import java.io.*;

/**
 * author: dulei
 * date: 18-4-28
 * desc: 读取csv文件
 */
public class TestRead {
    public static void main(String[] args) {
        try {
            String path = TestRead.class.getClassLoader().getResource("").getPath();
            String filePath = path + "audience example.csv";
            System.out.println(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));

//            通常使用的场景是：直接从磁盘中读取文件
//            BufferedReader reader = new BufferedReader(new FileReader("/home/dulei/Desktop/audience example.csv"));

            reader.readLine();//第一行信息，为标题信息，如果不需要标题，可以注释掉
            String line = null;
            while ((line = reader.readLine()) != null) {
                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件
                for (String column : item) {
                    System.out.print(column+"\t");
                }
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
