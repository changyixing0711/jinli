package com.xinzhi.admin.controller;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xinzhi.admin.model.RespBean;
import com.xinzhi.admin.pojo.SaleList;
import com.xinzhi.admin.pojo.SaleListGoods;
import com.xinzhi.admin.query.SaleListQuery;
import com.xinzhi.admin.service.ISaleListService;
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
 * 销售单表 前端控制器
 * </p>
 *
 * @author 小常
 * @since 2023-02-15
 */
@Controller
@RequestMapping("/sale")
public class SaleListController {
    @Resource
    private ISaleListService saleListService;

    @Resource
    private IUserService userService;

    /**
     * 销售出库主页
     * @return
     */
    @RequestMapping("index")
    public String index(Model model){
        model.addAttribute("saleNumber",saleListService.getNextSaleNumber());
        return "sale/sale";
    }


    @RequestMapping("save")
    @ResponseBody
    public RespBean save(SaleList saleList, String goodsJson, Principal principal){
        String userName = principal.getName();
        saleList.setUserId(userService.findUserByUserName(userName).getId());
        Gson gson = new Gson();
        List<SaleListGoods> slgList = gson.fromJson(goodsJson,new TypeToken<List<SaleListGoods>>(){}.getType());
        saleListService.saveSaleList(saleList,slgList);
        return RespBean.success("商品销售出库成功!");
    }

    /**
     * 销售单查询页
     * @return
     */
    @RequestMapping("searchPage")
    public String searchPage(){
        return "sale/sale_search";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> saleList(SaleListQuery saleListQuery){
        return saleListService.saleList(saleListQuery);
    }

}
