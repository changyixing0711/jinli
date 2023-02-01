package com.xinzhi.admin.controller;


import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.xinzhi.admin.model.CaptchaImageModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class CaptchaController {
    @Resource
    public DefaultKaptcha defaultKaptcha;

    @RequestMapping(value = "/image",method = RequestMethod.GET)
    public void kaptcha(HttpSession session, HttpServletResponse response) throws IOException{
        response.setDateHeader("Expires",0);
        response.setHeader("Cache-Control","no-store,no-cache,must-revalidate");
        response.addHeader("Cache-Control","post-check=0,pre-check=0");
        response.setHeader("Pragma","no-cache");
        response.setContentType("image/jpeg");

        //验证码文字
        String capText = defaultKaptcha.createText();
        //为了校验当前会话验证码存到session中,并设置两分钟后过期
        session.setAttribute("captcha_key",new CaptchaImageModel(capText,2*60));
        try(ServletOutputStream out = response.getOutputStream()) {
            BufferedImage bufferedImage = defaultKaptcha.createImage(capText);
            ImageIO.write(bufferedImage, "jpg", out);
            out.flush();
        }
    }




}
