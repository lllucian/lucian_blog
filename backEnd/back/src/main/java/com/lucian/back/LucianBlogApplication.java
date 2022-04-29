package com.lucian.back;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lingxiangdeng
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.lucian.back.dao", "com.lucian.common.dao"})
@ComponentScan(basePackages = {"com.lucian.common", "com.lucian.back"})
public class LucianBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(LucianBlogApplication.class, args);
    }

}
