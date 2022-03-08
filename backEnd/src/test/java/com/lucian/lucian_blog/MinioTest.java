package com.lucian.lucian_blog;

import com.lucian.lucian_blog.utils.MinIoUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.io.FileInputStream;

@SpringBootTest
public class MinioTest {

    @Test
    public void existsBucket(){
        Assert.isTrue(MinIoUtil.bucketExists("pic"));
    }

    @Test
    @SneakyThrows(Exception.class)
    public void uploadFile(){
        System.out.println(MinIoUtil.upload("pic", "409_bbbb-3.xls",new FileInputStream("/Users/lingxiangdeng/Downloads/409_bbbb-3.xls")));
    }
}
