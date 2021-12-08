package com.lucian.lucian_blog.config;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class FastDFSClient {
    static {
        try {
            String path = new ClassPathResource("fastdfs-client.properties").getPath();
            ClientGlobal.initByProperties(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String[] uploadFile(MultipartFile file) {
        try {
            //创建TrackerClient
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getTrackerServer();
            //创建StorageServer
            StorageServer storageServer = null;
            StorageClient1 client = new StorageClient1(trackerServer, storageServer);

            String fileName = file.getOriginalFilename();
            String fileExt = StringUtils.getFilenameExtension(fileName);
            //文件上传的元信息
            NameValuePair[] metaList = new NameValuePair[1];
            //这里第一个参数随意写，第二个参数是上传文件是命名的文件名
            metaList[0] = new NameValuePair("fileName", fileName);
            String uploadResult = client.upload_file1(file.getBytes(), fileExt, metaList);
            return uploadResult.split("/", 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getTrackerUrl(){
        try {
            //3.创建trackerclient对象
            TrackerClient tracker = new TrackerClient();
            //4.创建trackerserver 对象
            TrackerServer trackerServer = tracker.getTrackerServer();
            //tracker 的ip的信息
            String hostString = trackerServer.getInetSocketAddress().getHostString();

            //http://192.168.211.132:8080/group1/M00/00/00/wKjThF1aW9CAOUJGAAClQrJOYvs424.jpg img
            int g_tracker_http_port = ClientGlobal.getG_tracker_http_port();
            return "http://" + hostString + ":" + g_tracker_http_port;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
