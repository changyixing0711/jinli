package com.xinzhi.admin.controller;

import com.xinzhi.admin.model.RespBean;
import com.xinzhi.admin.pojo.Role;
import com.xinzhi.admin.pojo.User;
import com.xinzhi.admin.query.RoleQuery;
import com.xinzhi.admin.service.IRoleService;
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
     * 用户管理主页
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "role/role";
    }

    /**
     * 角色列表查询
     * @param roleQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> roleList(RoleQuery roleQuery){
    return iRoleService.roleList(roleQuery);
    }

    /**
     * 角色添加 更新管理页面
     * @param id
     * @param model
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
     * @param role
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public RespBean saveRole(Role role){
        iRoleService.saveRole(role);
        return RespBean.success("用户记录添加成功!");
    }

    /**
     * 角色记录更新接口
     * @param role
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public RespBean updateRole(Role role){
        iRoleService.updateRole(role);
        return RespBean.success("用户记录更新成功!");
    }

    /**
     * 角色记录删除接口
     * @param id
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public RespBean deleteRole(Integer id){
        iRoleService.deleteRole(id);
        return RespBean.success("用户记录删除成功!");
    }

    @RequestMapping("queryAllRoles")
    @ResponseBody
    public List<Map<String,Object>> queryAllRoles(Integer userId){
        return iRoleService.queryAllRoles(userId);
    }
}
