package com.lucian.back.utils;

import com.lucian.back.config.MinioConfig;
import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.List;

/**
 * @author lingxiangdeng
 */
@Component
public class MinIoUtil {

    MinioConfig minioConfig;

    @Autowired
    public void setMinioConfig(MinioConfig minioConfig) {
        this.minioConfig = minioConfig;
    }

    private static MinioClient minioClient;

    @PostConstruct
    public void init() {
        try {
            minioClient = MinioClient.builder().endpoint(minioConfig.getUrl()).credentials(minioConfig.getAccessKey(), minioConfig.getSecretKey()).build();
            createBucket(minioConfig.getBucketName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 桶名是否存在
     * @param bucketName 桶名
     * @return true：存在， false：不存在
     */
    @SneakyThrows(Exception.class)
    public static boolean bucketExists(String bucketName) {
        return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }

    /**
     * 创建 bucket
     *
     * @param bucketName:
     *            桶名
     * return:  void
     * date  : 2020/8/16 20:53
     */
    @SneakyThrows(Exception.class)
    public static void createBucket(String bucketName) {
        boolean isExist = bucketExists(bucketName);
        if (!isExist) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

    /**
     * 获取所有桶信息
     * @return 所有桶信息
     */
    @SneakyThrows(Exception.class)
    public static List<Bucket> getAllBuckets() {
        return minioClient.listBuckets();
    }

    /**
     * 上传文件，使用文件路径方式
     * @param bucketName 桶名
     * @param fileName 文件名
     * @param filePath 文件路径
     */
    @SneakyThrows(Exception.class)
    public static String upload(String bucketName, String fileName, String filePath) {
        minioClient.uploadObject(UploadObjectArgs.builder().bucket(bucketName).object(fileName).filename(filePath).build());
        return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                .method(Method.GET).bucket(bucketName).object(fileName).build());
    }

    /**
     * 上传文件，使用io流方式
     * @param bucketName 桶名
     * @param fileName 文件名
     * @param inputStream 文件io流
     */
    @SneakyThrows(Exception.class)
    public static void upload(String bucketName, String fileName, InputStream inputStream){
        minioClient.putObject(PutObjectArgs.builder().bucket(bucketName)
                .object(fileName).stream(inputStream, inputStream.available(),-1).build());
    }

    /**
     * 上传文件，使用io流方式
     * @param bucketName 桶名
     * @param fileName 文件名
     * @return 文件地址
     */
    @SneakyThrows(Exception.class)
    public static String findOne(String bucketName, String fileName){
        return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                .method(Method.GET).bucket(bucketName).object(fileName).build());
    }

}
