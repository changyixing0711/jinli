package com.xinzhi.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@MapperScan("com.xinzhi.admin.dao")
public class JinliAdminAppliocation {
    public static void main(String[] args) {
        SpringApplication.run(JinliAdminAppliocation.class,args);
//        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }
}