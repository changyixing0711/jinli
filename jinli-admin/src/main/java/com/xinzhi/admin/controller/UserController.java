package com.xinzhi.admin.controller;


import com.xinzhi.admin.exceptions.ParamsException;
import com.xinzhi.admin.model.RespBean;
import com.xinzhi.admin.pojo.User;
import com.xinzhi.admin.query.UserQuery;
import com.xinzhi.admin.service.IUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;

import java.security.Principal;
import java.util.Map;

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
    //TODO 未引入框架前的登陆
//    @RequestMapping("login")
//    @ResponseBody
//    public RespBean login(String userName, String password, HttpSession session) {
//        User user = userService.login(userName, password);
//        session.setAttribute("user", user);
//        return RespBean.success("用户登陆成功!");
//    }

    /**
     * 用户信息设置页面
     * @return 用户信息设置页面
     */
    @RequestMapping("setting")
    public String setting(Principal principal, Model model) {
       User user= userService.findUserByUserName(principal.getName());
        model.addAttribute("user",user);
        return "user/setting";
    }

    /**
     * 用户信息更新
     * @param user 用户对象
     * @return
     */
    @RequestMapping("updateUserInfo")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('1010')")
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
     * @param principal       security的缓存
     * @param oldPassword     旧密码
     * @param newPassword     新密码
     * @param confirmPassword 确认密码
     * @return
     */
    @RequestMapping("updateUserPassword")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('101001')")
    public RespBean updateUserPassword(Principal principal, String oldPassword, String newPassword, String confirmPassword) {
        userService.updateUserPassword(principal.getName(), oldPassword, newPassword, confirmPassword);
        return RespBean.success("密码修改成功");
    }

    /**
     * 用户管理页面
     * @return
     */
    @RequestMapping("index")
    @PreAuthorize("hasAnyAuthority('1010')")
    public String index(){
    return "user/user";
    }

    /**
     * 用户列表查询接口
     * @param userQuery 用户表分页属性
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('101003')")
    public Map<String,Object> userList(UserQuery userQuery){
    return userService.userList(userQuery);
    }

    /**
     * 添加 更新用户页面
     * @param id 用户id
     * @param model 用来存储数据的对象
     * @return
     */

    @RequestMapping("addOrUpdateUserPage")
    public String addOrUpdatePage(Integer id,Model model){
        if (null != id){
            model.addAttribute("user",userService.getById(id));
        }
        return "user/add_update";
    }

    /**
     * 用户记录添加接口
     * @param user 用户类
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('101004')")
    public RespBean saveUser(User user){
        userService.saveUser(user);
        return RespBean.success("用户记录添加成功!");
    }

    /**
     * 用户记录更新接口
     * @param user 用户类
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('101005')")
    public RespBean updateUser(User user){
        userService.updateUser(user);
        return RespBean.success("用户记录更新成功!");
    }

    /**
     * 用户记录批量删除接口
     * @param ids 要删除的用户id的数组
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('101006')")
    public RespBean deleteUser(Integer ids[]){
    userService.deleteUser(ids);
    return RespBean.success("用户记录删除成功!");
    }

    /**
     * 用户密码重置
     * @param id 用户id
     * @return
     */
    @RequestMapping("reset")
    @ResponseBody
    public RespBean reset(Integer id){
        userService.reset(id);
        return RespBean.success("密码重置成功!");
    }

}
