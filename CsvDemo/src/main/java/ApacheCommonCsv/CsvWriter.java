package ApacheCommonCsv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * author: dulei
 * date: 18-4-28
 * desc:
 */
public class CsvWriter {
    private static final String[] FILE_HEADER = {"用户名", "密码", "名称", "年龄"};

    public static void writeCsv(String filePath) {
        FileWriter fileWriter = null;
        CSVPrinter csvPrinter = null;
        //创建CSVFormat
        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER).withRecordSeparator('\n').withDelimiter('\t');
        try {
            fileWriter = new FileWriter(filePath);
            //初始化CsvPrinter
            csvPrinter = new CSVPrinter(fileWriter, csvFormat);
            //用户对象放入List
            List<User> userList = new ArrayList<User>();
            userList.add(new User("zhangsan", "123434", "张三", 25));
            userList.add(new User("lisi", "1234", "李四", 22));
            userList.add(new User("wangwu", "123434", "王五", 25));
            userList.add(new User("zhaoliu", "124", "赵六", 25));

            //遍历List写入写入CSV
            for (User user : userList) {
                List<String> record = new ArrayList<String>();
                record.add(user.userName);
                record.add(user.passWord);
                record.add(user.name);
                record.add(String.valueOf(user.age));
                csvPrinter.printRecord(record);
            }
            System.out.println("CSV文件创建成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
                csvPrinter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class User {
    public String userName;
    public String passWord;
    public String name;
    public int age;

    public User(String userName, String passWord, String name, int age) {
        this.userName = userName;
        this.passWord = passWord;
        this.name = name;
        this.age = age;
    }
}