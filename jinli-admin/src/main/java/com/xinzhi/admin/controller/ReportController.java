package com.xinzhi.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 乐字节  踏实教育 用心服务
 *
 * @author 乐字节--老李
 * @version 1.0
 *
 *
 * 统计报表
 */
@Controller
@RequestMapping("report")
public class ReportController {


    @RequestMapping("countSupplier")
    public String countSupplierPage(){
        return "count/supplier";
    }

    @RequestMapping("countCustomer")
    public String countCustomerPage(){
        return "count/customer";
    }

    /**
     * 商品采购统计
     * @return
     */
    @RequestMapping("countPurchase")
    public String countPurchase(){
        return "count/purchase";
    }

    /**
     * 商品销售统计
     * @return
     */
    @RequestMapping("countSale")
    public String countSale(){
        return "count/sale";
    }


    /**
     * 日销售统计
     * @return
     */
    @RequestMapping("countDaySale")
    public String countDaySale(){
        return "count/day_sale";
    }

    /**
     * 月销售统计
     * @return
     */
    @RequestMapping("countMonthSale")
    public String countMonthSale(){
        return "count/month_sale";
    }



}
