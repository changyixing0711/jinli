package com.xinzhi.admin.service.impl;

import com.xinzhi.admin.pojo.RoleMenu;
import com.xinzhi.admin.dao.RoleMenuMapper;
import com.xinzhi.admin.service.IRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色菜单表 服务实现类
 * </p>
 *
 * @author 小常
 * @since 2023-02-05
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

    @Override
    public List<Integer> queryRoleHasAllMenusById(Integer roleId) {
        return this.baseMapper.queryRoleHasAllMenusById(roleId);
    }

    @Override
    public List<String> findAuthoritiesByRoleName(List<String> roleNames) {
        return this.baseMapper.findAuthoritiesByRoleName(roleNames);
    }
}
