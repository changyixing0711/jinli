package com.xinzhi.admin.service;

import com.xinzhi.admin.pojo.RoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色菜单表 服务类
 * </p>
 *
 * @author 小常
 * @since 2023-02-05
 */
public interface IRoleMenuService extends IService<RoleMenu> {

    List<Integer> queryRoleHasAllMenusById(Integer roleId);

    List<String> findAuthoritiesByRoleName(List<String> roleNames);
}
