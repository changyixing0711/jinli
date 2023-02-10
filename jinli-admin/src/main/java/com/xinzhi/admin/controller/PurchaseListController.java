package com.xinzhi.admin.controller;


import com.xinzhi.admin.service.IPurchaseListService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * <p>
 * 进货单 前端控制器
 * </p>
 *
 * @author 小常
 * @since 2023-02-09
 */
@Controller
@RequestMapping("/purchase")
public class PurchaseListController {
    /**
     *
     * @return
     */
    @Resource
    private IPurchaseListService purchaseListService;

    @RequestMapping("index")
    public String index(Model model){
        //获取进货单号
        String purchaseNumber=purchaseListService.getNextPurchaseNumber();
        model.addAttribute("purchaseNumber",purchaseNumber);
        System.out.println(purchaseNumber);
        return "purchase/purchase";
    }


}
