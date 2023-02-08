package com.xinzhi.admin.service;

import com.xinzhi.admin.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinzhi.admin.query.RoleQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author 小常
 * @since 2023-02-03
 */
public interface IRoleService extends IService<Role> {

    Map<String, Object> roleList(RoleQuery roleQuery);

    void saveRole(Role role);

    Role findRoleByRoleName(String roleName);

    void updateRole(Role role);

    void deleteRole(Integer id);

    List<Map<String, Object>> queryAllRoles(Integer userId);

    void addGrant(Integer roleId, Integer[] mids);
}
