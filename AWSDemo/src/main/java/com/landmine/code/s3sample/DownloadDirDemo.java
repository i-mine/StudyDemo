package com.landmine.code.s3sample;

import com.amazonaws.services.s3.AmazonS3;

/**
 * author: dulei
 * date: 18-9-12
 * desc:
 */
public class DownloadDirDemo {
    public static void main(String[] args) {
        AmazonS3 client = S3Util.getClient();
        String bucket_name = "mob-emr-test";
        String key_prefix = "lei.du/result/";
        String dir_path = "/home/dulei/tmp/result/";
        boolean pause = false;
        S3Util.downloadDir(client, bucket_name, key_prefix, dir_path, pause);
    }
}
