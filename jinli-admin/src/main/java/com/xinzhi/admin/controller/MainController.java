package com.xinzhi.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    /**
     * 系统登陆页面
     * @return 登陆页面
     */
    @RequestMapping("index")
    public String index(){
        return "index";
    }

    /**
     * 系统主页面
     * @return 主页面
     */
    @RequestMapping("main")
    public String main(){
        return "main";
    }

    /**
     * 系统欢迎页
     * @return 欢迎页
     */
    @RequestMapping("welcome")
    public String welcome(){
        return "welcome";
    }

    /**
     * 系统退出
     * @return TODO 原生退出登陆
     */
//    @RequestMapping("signout")
//    public String signout(HttpSession session){
//        session.removeAttribute("user");
//    return "redirect:index";
//    }


}
