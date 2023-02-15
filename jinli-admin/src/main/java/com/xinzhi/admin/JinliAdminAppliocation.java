package com.xinzhi.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@MapperScan("com.xinzhi.admin.dao")
public class JinliAdminAppliocation extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(JinliAdminAppliocation.class,args);
//        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
        return builder.sources(JinliAdminAppliocation.class);
    }
}