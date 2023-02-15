package com.xinzhi.admin.controller;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xinzhi.admin.model.RespBean;
import com.xinzhi.admin.pojo.DamageList;
import com.xinzhi.admin.pojo.DamageListGoods;
import com.xinzhi.admin.query.DamageListQuery;
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
 * 报损单表 前端控制器
 * </p>
 *
 * @author 小常
 * @since 2023-02-14
 */
@Controller
@RequestMapping("/damage")
public class DamageListController {
    @Resource
    private IDamageListService damageListService;

    @Resource
    private IUserService userService;


    /**
     * 商品报损主页
     * @return
     */
    @RequestMapping("index")
    public String index(Model model){
        model.addAttribute("damageNumber",damageListService.getNextDamageNumber());
        return "damage/damage";
    }

    @RequestMapping("save")
    @ResponseBody
    public RespBean save(DamageList damageList, String goodsJson, Principal principal){
        String userName = principal.getName();
        damageList.setUserId(userService.findUserByUserName(userName).getId());
        Gson gson = new Gson();
        List<DamageListGoods> plgList = gson.fromJson(goodsJson,new TypeToken<List<DamageListGoods>>(){}.getType());
        damageListService.saveDamageList(damageList,plgList);
        return RespBean.success("商品报损出库成功!");
    }


    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> damageList(DamageListQuery damageListQuery){
        return damageListService.damageList(damageListQuery);
    }
}
