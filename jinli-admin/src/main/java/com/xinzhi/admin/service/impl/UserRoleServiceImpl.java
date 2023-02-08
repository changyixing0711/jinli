package com.xinzhi.admin.service.impl;

import com.xinzhi.admin.pojo.UserRole;
import com.xinzhi.admin.dao.UserRoleMapper;
import com.xinzhi.admin.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author 小常
 * @since 2023-02-03
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Override
    public List<String> findRolesByUserName(String userName) {
        return this.baseMapper.findRolesByUserName(userName);
    }
}
