package ApacheCommonCsv;

/**
 * author: dulei
 * date: 18-4-28
 * desc:
 */
public class Main {
    public static void main(String[] args) {
        String path = Main.class.getClassLoader().getResource("").getPath();
        String filePath = path + "audience example.csv";
        //读取csv文件
        CsvReader.readCsvFile(filePath);
        //写数据到csv文件
        CsvWriter.writeCsv("/home/dulei/tmp/usertest.csv");
    }
}
