package com.xinzhi.admin.dao;

import com.xinzhi.admin.pojo.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色菜单表 Mapper 接口
 * </p>
 *
 * @author 小常
 * @since 2023-02-05
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    List<Integer> queryRoleHasAllMenusById(Integer roleId);

    List<String> findAuthoritiesByRoleName(List<String> roleNames);
}
