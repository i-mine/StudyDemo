package com.landmine.code.s3sample;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * author: dulei
 * date: 18-9-1
 * desc:
 */
public class DownloadDemo {
    public static void main(String[] args) {
        String accessKey = "";//需要添加aws权限秘钥
        String secretKey = "";//需要添加aws权限秘钥
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);

        AmazonS3ClientBuilder builder = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.US_EAST_1.getName());

        AmazonS3 s3Client = builder.build();
        //本地路径
        String targetFilePath = "/home/dulei/Desktop/s3file.xlsx";
        String bucketName = "mob-emr-test";
        //s3路径
        String key = "lei.du/tmp/taggingresult0713-0716.xlsx";

        S3Object object = s3Client.getObject(new GetObjectRequest(bucketName, key));
        if (object != null) {
            System.out.println("Content-Type: " + object.getObjectMetadata().getContentType());
            InputStream input = null;
            FileOutputStream fileOutputStream = null;
            byte[] data = null;
            try {
                //获取文件流
                input = object.getObjectContent();
                data = new byte[input.available()];
                int len = 0;
                fileOutputStream = new FileOutputStream(targetFilePath);
                while ((len = input.read(data)) != -1) {
                    fileOutputStream.write(data, 0, len);
                }
                System.out.println("下载文件成功");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

