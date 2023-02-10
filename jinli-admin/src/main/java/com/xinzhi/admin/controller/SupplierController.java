package com.xinzhi.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xinzhi.admin.model.RespBean;
import com.xinzhi.admin.pojo.Supplier;
import com.xinzhi.admin.query.SupplierQuery;
import com.xinzhi.admin.query.UserQuery;
import com.xinzhi.admin.service.ISupplierService;
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
 * 供应商表 前端控制器
 * </p>
 *
 * @author 小常
 * @since 2023-02-10
 */
@Controller
@RequestMapping("/supplier")
public class SupplierController {
    @Resource
    private ISupplierService supplierService;

    /**
     * 供应商管理主页
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "supplier/supplier";
    }


    /**
     * 供应商查询接口
     * @param supplierQuery 供应商分页属性
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> supplierList(SupplierQuery supplierQuery){
        return supplierService.supplierList(supplierQuery);
    }

    @RequestMapping("addOrUpdateSupplierPage")
    public String addOrUpdateSupplierPage(Integer id, Model model){
        if (null!=id){
            model.addAttribute("supplier",supplierService.getById(id));
        }
        return "supplier/add_update";
    }

    /**
     * 添加供应商
     * @param supplier
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public RespBean saveSupplier(Supplier supplier){
        supplierService.savesupplier(supplier);
        return RespBean.success("供应商添加成功");
    }

    /**
     * 修改供应商
     * @param supplier
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public RespBean updateSupplier(Supplier supplier){
        supplierService.updatesupplier(supplier);
        return RespBean.success("供应商添加成功");
    }

    /**
     * 删除供应商
     * @param ids
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public RespBean deleteSupplier(Integer[] ids){
        supplierService.deleteSupplier(ids);
        return RespBean.success("供应商删除成功");
    }

    /**
     * 查询供应商
     * @return
     */
    @RequestMapping("allGoodsSuppliers")
    @ResponseBody
    public List<Supplier> allGoodsSuppliers(){
        return supplierService.list(new QueryWrapper<Supplier>().eq("is_del",0));
    }
}
