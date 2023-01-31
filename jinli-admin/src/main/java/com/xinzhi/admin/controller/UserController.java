package com.xinzhi.admin.controller;


import com.xinzhi.admin.exceptions.ParamsException;
import com.xinzhi.admin.model.RespBean;
import com.xinzhi.admin.pojo.User;
import com.xinzhi.admin.service.IUserService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author 小常
 * @since 2023-01-31
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @RequestMapping("login")
    @ResponseBody
    public RespBean login(String userName, String password, HttpSession session) {
        User user = userService.login(userName, password);
        session.setAttribute("user", user);
        System.out.println(user);
        return RespBean.success("用户登陆成功!");
    }

    /**
     * 用户信息设置页面
     *
     * @return
     */
    @RequestMapping("setting")
    public String setting(HttpSession session) {
        User user = (User) session.getAttribute("user");
        userService.getById(user.getId());
        session.setAttribute("user", userService.getById(user.getId()));
        return "user/setting";
    }

    /**
     * 用户信息更新
     *
     * @param user
     * @return
     */
    @RequestMapping("updateUserInfo")
    @ResponseBody
    public RespBean updateUserInfo(User user) {
        userService.updateUserInfo(user);
        return RespBean.success("用户信息更新成功");
    }

    /**
     * 用户修改密码界面
     *
     * @return
     */
    @RequestMapping("password")
    public String password() {
        return "user/password";
    }

    /**
     * 用户密码修改
     *
     * @param session
     * @param oldPassword     旧密码
     * @param newPassword     新密码
     * @param confirmPassword 确认密码
     * @return
     */
    @RequestMapping("updateUserPassword")
    @ResponseBody
    public RespBean updateUserPassword(HttpSession session, String oldPassword, String newPassword, String confirmPassword) {
        User user = (User) session.getAttribute("user");
        userService.updateUserPassword(user.getUserName(), oldPassword, newPassword, confirmPassword);
        return RespBean.success("密码修改成功");
    }
}
