package com.xinzhi.admin.controller;

import com.xinzhi.admin.model.RespBean;
import com.xinzhi.admin.pojo.Role;
import com.xinzhi.admin.pojo.User;
import com.xinzhi.admin.query.RoleQuery;
import com.xinzhi.admin.service.IRoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Resource
    private IRoleService iRoleService;
    /**
     * 角色管理主页
     * @return 角色管理主页
     */
    @RequestMapping("index")
    @PreAuthorize("hasAnyAuthority('1020')")
    public String index(){
        return "role/role";
    }

    /**
     * 角色列表查询
     * @param roleQuery 角色表分页属性
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('102001')")
    public Map<String,Object> roleList(RoleQuery roleQuery){
    return iRoleService.roleList(roleQuery);
    }

    /**
     * 角色添加 更新管理页面
     * @param id  角色id
     * @param model  用来存储数据的对象
     * @return
     */
    @RequestMapping("addOrUpdateRolePage")
    public String addOrUpdatePage(Integer id, Model model){
        if (null != id){
            model.addAttribute("role",iRoleService.getById(id));
        }
        return "role/add_update";
    }

    /**
     * 角色记录添加接口
     * @param role 角色类
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('102002')")
    public RespBean saveRole(Role role){
        iRoleService.saveRole(role);
        return RespBean.success("用户记录添加成功!");
    }

    /**
     * 角色记录更新接口
     * @param role 角色类
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('102003')")
    public RespBean updateRole(Role role){
        iRoleService.updateRole(role);
        return RespBean.success("用户记录更新成功!");
    }

    /**
     * 角色记录删除接口
     * @param id 角色id
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('102004')")
    public RespBean deleteRole(Integer id){
        iRoleService.deleteRole(id);
        return RespBean.success("用户记录删除成功!");
    }

    /**
     * 根据用户id查用户的全部权限
     * @param userId 用户id
     * @return
     */
    @RequestMapping("queryAllRoles")
    @ResponseBody
    public List<Map<String,Object>> queryAllRoles(Integer userId){
        return iRoleService.queryAllRoles(userId);
    }

    /**
     * 权限添加页面
     * @param roleId 角色id
     * @param model 用来存储数据的对象
     * @return
     */
    @RequestMapping("toAddGrantPage")
    public String toAddGrantPage(Integer roleId,Model model){
    model.addAttribute("roleId",roleId);
    return "role/grant";
    }


    /**
     * 角色授权接口
     * @param roleId 角色id
     * @param mids  菜单id
     * @return
     */
    @RequestMapping("addGrant")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('102005')")
    public RespBean addGrant(Integer roleId,Integer [] mids){
    iRoleService.addGrant(roleId,mids);
    return RespBean.success("角色记录授权成功!");
    }
}
