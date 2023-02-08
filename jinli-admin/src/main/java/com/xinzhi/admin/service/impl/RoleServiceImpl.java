package com.xinzhi.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinzhi.admin.pojo.Role;
import com.xinzhi.admin.dao.RoleMapper;
import com.xinzhi.admin.pojo.RoleMenu;
import com.xinzhi.admin.pojo.User;
import com.xinzhi.admin.pojo.UserRole;
import com.xinzhi.admin.query.RoleQuery;
import com.xinzhi.admin.query.UserQuery;
import com.xinzhi.admin.service.IRoleMenuService;
import com.xinzhi.admin.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinzhi.admin.utils.AssertUtil;
import com.xinzhi.admin.utils.PageResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author 小常
 * @since 2023-02-03
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private UserRoleServiceImpl userRoleService;
    @Resource
    private IRoleMenuService roleMenuService;


    @Override
    public Map<String, Object> roleList(RoleQuery roleQuery) {
        Page<Role> page = new Page<Role>(roleQuery.getPage(),roleQuery.getLimit());
        QueryWrapper<Role> queryWrapper = new QueryWrapper<Role>();
        queryWrapper.eq("is_del",0);
        if (StringUtils.isNotEmpty(roleQuery.getRoleName())){
            queryWrapper.like("name",roleQuery.getRoleName());
        }
        page = this.baseMapper.selectPage(page,queryWrapper);

       return PageResultUtil.getResult(page.getTotal(),page.getRecords());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void saveRole(Role role) {
        AssertUtil.isTrue(StringUtils.isBlank(role.getName()),"请输入角色名");
        AssertUtil.isTrue(null !=this.findRoleByRoleName(role.getName()),"角色已存在");
        role.setIsDel(0);
        AssertUtil.isTrue(!(this.save(role)),"角色添加失败!");
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return this.baseMapper. selectOne(new QueryWrapper<Role>().eq("is_del",0).eq("name",roleName));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateRole(Role role) {
        AssertUtil.isTrue(StringUtils.isBlank(role.getName()),"请输入角色名");
        Role temp=this.findRoleByRoleName(role.getName());
        AssertUtil.isTrue(null !=temp && !(temp.getId().equals(role.getId())),"角色已存在");
        role.setIsDel(0);
        AssertUtil.isTrue(!(this.updateById(role)),"角色更新失败!");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deleteRole(Integer id) {
        /**
         * 删除
         */
        int count=userRoleService.count(new QueryWrapper<UserRole>().in("role_id",id));
        if (count>0){
            AssertUtil.isTrue(!(userRoleService.remove(new QueryWrapper<UserRole>().in("role_id",id))),"用户记录删除失败!");
        }
        AssertUtil.isTrue(null == id,"请选择待删除的记录");
        Role role =this.getById(id);
        AssertUtil.isTrue(null == role,"待删除记录不存在!");
        role.setIsDel(1);
        AssertUtil.isTrue(!(this.updateById(role)),"用户记录删除失败!");
    }

    @Override
    public List<Map<String, Object>> queryAllRoles(Integer userId) {
        return this.baseMapper.queryAllRoles(userId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void addGrant(Integer roleId, Integer[] mids) {
        /**
         * 1.参数校验
         *      roleId 非空 必须存在
         * 2. 授权 第一次授权
         *        批量添加
         *        第二次授权
         *        如果存在原始权限 先删除原始权限  然后添加新的权限记录
         *        如果不存在 那就直接批量添加
         *        不管之前的权限是否存在 先去执行一个查询 如果存在 直接删除(根据角色id)
         *        执行批量添加（数组非空 数量>0）
         */
        Role role = this.getById(roleId);
        AssertUtil.isTrue(null == this.getById(roleId),"待删除的角色记录不存在");
        int count=roleMenuService.count(new QueryWrapper<RoleMenu>().eq("role_id",roleId));
        if (count>0){
            AssertUtil.isTrue(!(roleMenuService.remove(new QueryWrapper<RoleMenu>().eq("role_id",roleId))),"角色授权失败");
        }
        if (null !=mids && mids.length>0){
            ArrayList<RoleMenu> roleMenus = new ArrayList<RoleMenu>();
            for (Integer mid : mids) {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(mid);
                roleMenus.add(roleMenu);
            }
            AssertUtil.isTrue(!(roleMenuService.saveBatch(roleMenus)),"角色授权失败!");
        }
    }
}
