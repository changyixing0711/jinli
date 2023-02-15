package com.xinzhi.admin.controller;


import com.xinzhi.admin.query.ReturnListGoodsQuery;
import com.xinzhi.admin.service.IReturnListGoodsService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 退货单商品表 前端控制器
 * </p>
 *
 * @author 小常
 * @since 2023-02-15
 */
@Controller
@RequestMapping("/returnListGoods")
public class ReturnListGoodsController {
    @Resource
    private IReturnListGoodsService returnListGoodsService;


    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> returnListGoodsList(ReturnListGoodsQuery returnListGoodsQuery){
        return returnListGoodsService.returnListGoodsList(returnListGoodsQuery);
    }
}
