package com.xinzhi.admin.model;

import java.time.LocalDateTime;

public class CaptchaImageModel {
    private String code;

    private LocalDateTime expireTime;

    public CaptchaImageModel(String code,int expireAfterSeconds){
        this.code=code;
        this.expireTime=LocalDateTime.now().plusSeconds(expireAfterSeconds);
    }
    //验证码是否失效
    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }

    public String getCode(){return code;}
}
