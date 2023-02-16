package com.xinzhi.admin.controller;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xinzhi.admin.model.RespBean;
import com.xinzhi.admin.pojo.CustomerReturnList;
import com.xinzhi.admin.pojo.CustomerReturnListGoods;
import com.xinzhi.admin.query.CustomerReturnListQuery;
import com.xinzhi.admin.service.ICustomerReturnListService;
import com.xinzhi.admin.service.IUserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户退货单表 前端控制器
 * </p>
 *
 * @author 小常
 * @since 2023-02-15
 */
@Controller
@RequestMapping("/customerReturn")
public class CustomerReturnListController {
    @Resource
    private ICustomerReturnListService customerReturnListService;


    @Resource
    private IUserService userService;

    /**
     * 客户退货主页
     * @return
     */
    @RequestMapping("index")
    public String index(Model model){
        model.addAttribute("customerReturnNumber",customerReturnListService.getNextCustomerReturnNumber());
        return "customerReturn/customer_return";
    }

    @RequestMapping("save")
    @ResponseBody
    public RespBean save(CustomerReturnList customerReturnList, String goodsJson, Principal principal){
        String userName = principal.getName();
        customerReturnList.setUserId(userService.findUserByUserName(userName).getId());
        Gson gson = new Gson();
        List<CustomerReturnListGoods> slgList = gson.fromJson(goodsJson,new TypeToken<List<CustomerReturnListGoods>>(){}.getType());
        customerReturnListService.saveCustomerReturnList(customerReturnList,slgList);
        return RespBean.success("商品退货入库成功!");
    }

    @RequestMapping("delete")
    @ResponseBody
    public RespBean delete(Integer id){
        customerReturnListService.deleteCustomerList(id);
        return RespBean.success("删除成功");
    }


    /**
     * 退货单查询页
     * @return
     */
    @RequestMapping("searchPage")
    public String searchPage(){
        return "customerReturn/customer_return_search";
    }


    /**
     * 退货单列表
     * @param customerReturnListQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> customerReturnList(CustomerReturnListQuery customerReturnListQuery){
        return customerReturnListService.customerReturnList(customerReturnListQuery);
    }

}
