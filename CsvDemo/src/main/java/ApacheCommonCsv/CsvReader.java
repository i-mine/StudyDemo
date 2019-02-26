package ApacheCommonCsv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * author: dulei
 * date: 18-4-28
 * desc:
 */
public class CsvReader {
    public static void readCsvFile(String filePath) {
        //创建CSVFormat header mapping
        CSVFormat format = CSVFormat.DEFAULT.withFirstRecordAsHeader();
        try {
            Reader in = new FileReader(new File(filePath));
            Iterable<CSVRecord> records = format.parse(in);
            for (CSVRecord record : records) {
                String dmpId = record.get(0);
                String dmpName = record.get(1);
                String audienceId = record.get(2);
                String audienceName = record.get(3);
                List array = new ArrayList<String>();
                for (int i = 4; i < record.size(); i++) {
                    if (!record.get(i).equals("")) {
                        array.add(record.get(i));
                    }
                }
                String tag = array.toString();
                System.out.println(new Audience(dmpId, dmpName, audienceId, audienceName, tag));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
