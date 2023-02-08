package com.xinzhi.admin.dao;

import com.xinzhi.admin.pojo.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户角色表 Mapper 接口
 * </p>
 *
 * @author 小常
 * @since 2023-02-03
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<String> findRolesByUserName(String userName);
}
