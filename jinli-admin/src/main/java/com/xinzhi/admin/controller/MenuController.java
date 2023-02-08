package com.xinzhi.admin.controller;


import com.xinzhi.admin.dto.TreeDto;
import com.xinzhi.admin.model.RespBean;
import com.xinzhi.admin.pojo.Menu;
import com.xinzhi.admin.service.IMenuService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author 小常
 * @since 2023-02-04
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private IMenuService menuService;

    /**
     * 返回所有菜单数据
     * @return 菜单数据
     */
    @RequestMapping("queryAllMenus")
    @ResponseBody

    public List<TreeDto> queryAllMenus(Integer roleId){
    return menuService.queryAllMenus(roleId);
    }

    /**
     * 菜单主页
     * @return 菜单主页
     */
    @RequestMapping("/index")
    public String index(){
        return "menu/menu";
    }

    /**
     * 菜单列表查询接口
     * @return 菜单列表
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> menuList(){
        return menuService.menuList();
    }

    /**
     * 添加菜单页
     * @param grade 权限码
     * @param pId   角色id
     * @param model 用来存储数据的对象
     * @return 添加菜单页
     */
    @RequestMapping("addMenuPage")
    public String addMenuPage(Integer grade, Integer pId, Model model){
        model.addAttribute("grade",grade);
        model.addAttribute("pId",pId);
        return "menu/add";
    }

    /**
     * 菜单记录添加
     * @param menu 菜单实体类
     * @return 菜单记录添加成功
     */
    @RequestMapping("save")
    @ResponseBody
    public RespBean saveMenu(Menu menu){
    menuService.saveMenu(menu);
    return RespBean.success("菜单记录添加成功!");
    }

    /**
     * 更新菜单页
     * @param id 选择id
     * @param model 用来存储数据的对象
     * @return 修改页面
     */
    @RequestMapping("updateMenuPage")
    public String updateMenuPage(Integer id,Model model){
    model.addAttribute("menu",menuService.getById(id));
    return "menu/update";
    }

    /**
     * 菜单修改
     * @param menu 菜单实体类
     * @return 菜单记录更新成功
     */
    @RequestMapping("update")
    @ResponseBody
    public RespBean updateMenu(Menu menu){
        menuService.updateMenu(menu);
        return RespBean.success("菜单记录更新成功");
    }

    /**
     * 菜单删除
     * @param id 选择id
     * @return 菜单删除成功
     */
    @RequestMapping("delete")
    @ResponseBody
    public RespBean deleteMenu(Integer id){
        menuService.deleteMenuById(id);
        return RespBean.success("菜单删除成功");
    }
}
