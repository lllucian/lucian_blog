package com.lucian.lucian_blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.lucian.lucian_blog.dao")
public class LucianBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(LucianBlogApplication.class, args);
    }

}
