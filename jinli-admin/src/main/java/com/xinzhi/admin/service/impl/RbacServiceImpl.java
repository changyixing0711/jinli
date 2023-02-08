package com.xinzhi.admin.service.impl;

import com.xinzhi.admin.service.IRbacService;
import com.xinzhi.admin.service.IRoleMenuService;
import com.xinzhi.admin.service.IUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class RbacServiceImpl implements IRbacService {
    @Resource
    private IUserRoleService userRoleService;

    @Resource
    private IRoleMenuService roleMenuService;

    @Override
    public List<String> findRolesByUserName(String userName) {
        return userRoleService.findRolesByUserName(userName);
    }

    @Override
    public List<String> findAuthoritiesByRoleName(List<String> roleNames) {
        return roleMenuService.findAuthoritiesByRoleName(roleNames);
    }
}
