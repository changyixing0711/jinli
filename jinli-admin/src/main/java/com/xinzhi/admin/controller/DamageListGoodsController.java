package com.xinzhi.admin.controller;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xinzhi.admin.model.RespBean;
import com.xinzhi.admin.pojo.DamageList;
import com.xinzhi.admin.pojo.DamageListGoods;
import com.xinzhi.admin.query.DamageListGoodsQuery;
import com.xinzhi.admin.service.IDamageListGoodsService;
import com.xinzhi.admin.service.IDamageListService;
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
 * 报损单商品表 前端控制器
 * </p>
 *
 * @author 小常
 * @since 2023-02-14
 */
@Controller
@RequestMapping("/damageListGoods")
public class DamageListGoodsController {
    @Resource
    private IDamageListGoodsService damageListGoodsService;

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> damageListGoodsList(DamageListGoodsQuery damageListGoodsQuery) {
        return damageListGoodsService.damageListGoodsList(damageListGoodsQuery);
    }
}


