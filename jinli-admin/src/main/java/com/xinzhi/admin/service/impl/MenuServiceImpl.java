package com.xinzhi.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xinzhi.admin.dto.TreeDto;
import com.xinzhi.admin.pojo.Menu;
import com.xinzhi.admin.dao.MenuMapper;
import com.xinzhi.admin.pojo.RoleMenu;
import com.xinzhi.admin.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinzhi.admin.service.IRoleMenuService;
import com.xinzhi.admin.utils.AssertUtil;
import com.xinzhi.admin.utils.PageResultUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author 小常
 * @since 2023-02-04
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Resource
    private IRoleMenuService roleMenuService;

    @Override
    public List<TreeDto> queryAllMenus(Integer roleId) {
        List<TreeDto> treeDtos=this.baseMapper.queryAllMenus();
        List<Integer> roleHasMenuIds=roleMenuService.queryRoleHasAllMenusById(roleId);
        if (CollectionUtils.isNotEmpty(roleHasMenuIds)){
            treeDtos.forEach(treeDto -> {
                if (roleHasMenuIds.contains(treeDto.getId())){
                    treeDto.setChecked(true);
                }
            });
        }
        return treeDtos;
    }

    @Override
    public Map<String, Object> menuList() {

        List<Menu> menus =this.list(new QueryWrapper<Menu>().eq("is_del",0));
        return PageResultUtil.getResult((long)menus.size(),menus);
    }

    @Override
    public Menu findMenuByNameAndGrade(String menuName,Integer grade) {
        return this.getOne(new QueryWrapper<Menu>().eq("is_del",0).eq("name",menuName).eq("grade",grade));
    }

    @Override
    public Menu findMenuByAclValue(String aclValue) {
        return this.getOne(new QueryWrapper<Menu>().eq("is_del",0).eq("acl_value",aclValue));
    }

    @Override
    public Menu findMenuById(Integer id) {
        return this.getOne(new QueryWrapper<Menu>().eq("is_del",0).eq("id",id));
    }

    @Override
    public Menu findMenuByGradeAndUrl(String url, Integer grade) {
        return this.getOne(new QueryWrapper<Menu>().eq("is_del",0).eq("url",url).eq("grade",grade));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void saveMenu(Menu menu) {
        /**
         * 1.参数校验
         *   菜单名 不能为空
         *   菜单层级 grade(0.1.2.3) 只支持三级菜单
         * 2.同一层级下
         *   菜单名不能重复
         * 3.权限码
         *    非空
         *    全局唯一
         * 4.上级菜单
         *      上级菜单 必须存在
         * 5.url 判断
         *       菜单属于二级菜单 url 不可以重复的
         * 6.设置参数值
         *      是否删除  is_del 类型 state
         */
        AssertUtil.isTrue(StringUtils.isBlank(menu.getName()),"请输入菜单名!");
        Integer grade = menu.getGrade();
        AssertUtil.isTrue(null==grade||!(grade==0||grade==1||grade==2),"菜单层级不合法!");
        AssertUtil.isTrue(null !=this.findMenuByNameAndGrade(menu.getName(),menu.getGrade()),"该层级下菜单已存在!");
        AssertUtil.isTrue(null !=this.findMenuByAclValue(menu.getAclValue()),"权限码已存在!");
        AssertUtil.isTrue(null == menu.getPId() || null == this.findMenuById(menu.getPId()),"请指定上级菜单");
        if(grade==1){
            //二级菜单
        AssertUtil.isTrue(null !=this.findMenuByGradeAndUrl(menu.getUrl(),menu.getGrade()),"该层级下url 不可重复");
        }
        menu.setIsDel(0);
        menu.setState(0);
        AssertUtil.isTrue(!(this.save(menu)),"菜单添加失败!");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateMenu(Menu menu) {
        /**
         * 1.参数校验
         *   菜单名 不能为空
         *   待更新的记录必须存在
         *   菜单层级 grade(0.1.2.3) 只支持三级菜单
         * 2.同一层级下
         *   菜单名不能重复
         * 3.权限码
         *    非空
         *    全局唯一
         * 4.上级菜单
         *      上级菜单 必须存在
         * 5.url 判断
         *       菜单属于二级菜单 url 不可以重复的
         */
        AssertUtil.isTrue(null == menu.getId() || null==this.findMenuById(menu.getId()),"待更新的记录不存在");
        AssertUtil.isTrue(StringUtils.isBlank(menu.getName()),"菜单名不能为空!");
        Integer grade = menu.getGrade();
        AssertUtil.isTrue(null==grade||!(grade==0||grade==1||grade==2),"菜单层级不合法!");
        Menu temp=this.findMenuByNameAndGrade(menu.getName(),menu.getGrade());
        AssertUtil.isTrue(null !=temp && !(temp.getId().equals(menu.getId())),"该层级下菜单已存在!");
        temp=this.findMenuByAclValue(menu.getAclValue());
        AssertUtil.isTrue(null !=temp && !(temp.getId().equals(menu.getId())),"该权限码已存在!");
        AssertUtil.isTrue(null == menu.getPId() || null == this.findMenuById(menu.getPId()),"请指定上级菜单");
        if(grade==1){
            //二级菜单
            temp=this.findMenuByGradeAndUrl(menu.getUrl(),menu.getGrade());
            AssertUtil.isTrue(null == menu.getPId() || null == this.findMenuById(menu.getPId()),"该层级下url 不可重复");
        }
        AssertUtil.isTrue(!(this.updateById(menu)),"菜单记录更新失败!");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deleteMenuById(Integer id) {
        /**
         *1.参数校验
         *      记录必须存在
         * 2.如果存在子菜单
         *      不允许直接删除 上级菜单
         * 3.角色菜单表关联
         */
        Menu menu=this.findMenuById(id);
        AssertUtil.isTrue(null == id || null==menu,"待删除的记录不存在!");
        int count=this.count(new QueryWrapper<Menu>().eq("is_del",0).eq("p_id",id));
        AssertUtil.isTrue(count>0,"存在子菜单,不允许直接删除!");
        count=roleMenuService.count(new QueryWrapper<RoleMenu>().eq("menu_id",id));
        if (count>0){
        AssertUtil.isTrue(!(roleMenuService.remove(new QueryWrapper<RoleMenu>().eq("menu_id",id))),"菜单删除失败!");
        }
        menu.setIsDel(1);
        AssertUtil.isTrue(!(this.updateById(menu)),"菜单删除失败!");
    }
}
