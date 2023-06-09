package com.xinzhi.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xinzhi.admin.model.RespBean;
import com.xinzhi.admin.pojo.Customer;
import com.xinzhi.admin.query.CustomerQuery;
import com.xinzhi.admin.service.ICustomerService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户表 前端控制器
 * </p>
 *
 * @author 小常
 * @since 2023-02-15
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Resource
    private ICustomerService customerService;

    /**
     * 客户管理主页
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "customer/customer";
    }


    /**
     * 客户列表查询
     * @param customerQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> customerList(CustomerQuery customerQuery){
        return customerService.customerList(customerQuery);
    }

    @RequestMapping("addOrUpdateCustomerPage")
    public String addRolePage(Integer id, Model model){
        if(null !=id){
            model.addAttribute("customer",customerService.getById(id));
        }
        return "customer/add_update";
    }


    /**
     * 保存客户
     * @param Customer
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public RespBean saveCustomer(Customer Customer){
        customerService.saveCustomer(Customer);
        return RespBean.success("记录添加成功");
    }

    /**
     * 更新客户
     * @param Customer
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public RespBean updateCustomer(Customer Customer){
        customerService.updateCustomer(Customer);
        return RespBean.success("记录更新成功");
    }

    /**
     * 删除客户
     * @param ids
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public RespBean deleteCustomer(Integer[] ids){
        customerService.deleteCustomer(ids);
        return RespBean.success("客户记录删除成功");
    }


    @RequestMapping("allCustomers")
    @ResponseBody
    public List<Customer> allCustomers(){
        return customerService.list(new QueryWrapper<Customer>().eq("is_del",0));
    }

}
