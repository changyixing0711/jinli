package com.xinzhi.admin.service;

import com.xinzhi.admin.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinzhi.admin.query.UserQuery;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 小常
 * @since 2023-01-31
 */
public interface IUserService extends IService<User> {
    /**
     * 用户登陆方法 //TODO 未上框架前的登陆方法
     * @param userName 用户名
     * @param password 密码
     * @return
     */
//    User login(String userName, String password);

    /**
     * 根据用户名查询用户记录
     * @param userName 用户名
     * @return
     */
    User findUserByUserName(String userName);

    /**
     * 修改用户信息
     * @param user
     */
    void updateUserInfo(User user);

    /**
     * 修改用户密码
     * @param userName 用户账号
     * @param oldPassword 用户旧密码
     * @param newPassword 用户新密码
     * @param confirmPassword 二次确认密码
     */
    void updateUserPassword(String userName, String oldPassword, String newPassword, String confirmPassword);

    Map<String, Object> userList(UserQuery userQuery);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(Integer[] ids);
}
