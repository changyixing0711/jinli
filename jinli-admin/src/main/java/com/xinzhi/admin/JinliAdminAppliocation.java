package com.xinzhi.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xinzhi.admin.dao")
public class JinliAdminAppliocation {
    public static void main(String[] args) {
        SpringApplication.run(JinliAdminAppliocation.class,args);
    }
}