package com.xinzhi.admin.service;

import com.xinzhi.admin.pojo.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author 小常
 * @since 2023-02-03
 */
public interface IUserRoleService extends IService<UserRole> {

    List<String> findRolesByUserName(String userName);
}
