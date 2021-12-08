package com.lucian.lucian_blog.config;

import org.csource.fastdfs.ClientGlobal;
import org.springframework.core.io.ClassPathResource;

public class FastDFSClient {
    static {
        try{
            String path = new ClassPathResource("fastdfs-client.properties").getPath();
            ClientGlobal.initByProperties(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
