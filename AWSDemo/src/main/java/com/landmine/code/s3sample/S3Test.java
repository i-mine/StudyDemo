package com.landmine.code.s3sample;

import com.amazonaws.services.s3.AmazonS3;
import com.datastax.driver.core.*;
import com.landmine.code.CassandraConnector;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.zip.GZIPInputStream;

/**
 * author: dulei
 * date: 18-9-1
 * desc:
 */
public class S3Test {
    public static void main(String[] args) {
        AmazonS3 client = S3Util.getClient();
        String bucketName = "mob-emr-test";
        String file = "part-00097.gz";
        String key = "huifang/m_sys_model/user_history_action_v1/region_install_pkg/se_sg/2018/08/31/" + file;
        String localPath = "/home/dulei/tmp/" + file;
//         S3Util.download(client,bucketName,key,localPath);
        File user_action_file = new File(localPath);
        CassandraConnector connector = new CassandraConnector();
//        connector.connect("172.31.29.170", 9042);
        connector.connect("127.0.0.1", 9042);
        Session session = connector.getSession();
        int Batch = 1024;
        try {
            GZIPInputStream gzipInputStream = new GZIPInputStream(new FileInputStream(user_action_file));
            BufferedReader reader = new BufferedReader(new InputStreamReader(gzipInputStream));
            int i = 20;
            BatchStatement batchStatement = new BatchStatement();
            batchStatement.setConsistencyLevel(ConsistencyLevel.LOCAL_ONE);
            int batch = 1;
            while (true && i-- > 0) {
                String line = reader.readLine();
                if (line == null) break;
                String[] arry = StringUtils.split(line, "\t");
                String devId = arry[0];
                String pkg = arry[1];
                BoundStatement insertBind1 = session.prepare("INSERT INTO user_action.user_pkg_test(device_id,pkg) VALUES(?,?);")
                        .bind(devId, pkg);
                batchStatement.add(insertBind1);
                if (batch == 5) {
                    session.execute(batchStatement);
                    batch = 0;
                    insertBind1 = null;
                    batchStatement.clear();
                }
                batch++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connector.close();
        }
    }
}
