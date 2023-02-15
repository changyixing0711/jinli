package com.xinzhi.admin.controller;


import com.xinzhi.admin.query.saleListGoodsQuery;
import com.xinzhi.admin.service.ISaleListGoodsService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 销售单商品表 前端控制器
 * </p>
 *
 * @author 小常
 * @since 2023-02-15
 */
@Controller
@RequestMapping("/saleListGoods")
public class SaleListGoodsController {
    @Resource
    private ISaleListGoodsService saleListGoodsService;

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> saleListGoodsList(saleListGoodsQuery saleListGoodsQuery){
        return saleListGoodsService.saleListGoodsList(saleListGoodsQuery);
    }
}
