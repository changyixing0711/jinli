package com.xinzhi.admin.controller;


import com.xinzhi.admin.query.CustomerReturnListGoodsQuery;
import com.xinzhi.admin.service.ICustomerReturnListGoodsService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 客户退货单商品表 前端控制器
 * </p>
 *
 * @author 小常
 * @since 2023-02-15
 */
@Controller
@RequestMapping("/customer-return-list-goods")
public class CustomerReturnListGoodsController {
    @Resource
    private ICustomerReturnListGoodsService customerReturnListGoodsService;

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> customerReturnListGoodsList(CustomerReturnListGoodsQuery customerReturnListGoodsQuery){
        return customerReturnListGoodsService.customerReturnListGoodsList(customerReturnListGoodsQuery);
    }
}
