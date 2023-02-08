package com.xinzhi.admin.dao;

import com.xinzhi.admin.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author 小常
 * @since 2023-02-03
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Map<String, Object>> queryAllRoles(Integer userId);
}
