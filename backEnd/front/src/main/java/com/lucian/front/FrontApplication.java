package com.lucian.front;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lingxiangdeng
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@MapperScan(basePackages = {"com.lucian.front.dao", "com.lucian.common.dao"})
@ComponentScan(basePackages = {"com.lucian.common", "com.lucian.front"})
public class FrontApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrontApplication.class, args);
    }
}
