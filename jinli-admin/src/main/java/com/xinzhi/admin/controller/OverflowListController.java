package com.xinzhi.admin.controller;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xinzhi.admin.model.RespBean;
import com.xinzhi.admin.pojo.OverflowList;
import com.xinzhi.admin.pojo.OverflowListGoods;
import com.xinzhi.admin.query.OverFlowListQuery;
import com.xinzhi.admin.service.IOverflowListService;
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
 * 报溢单表 前端控制器
 * </p>
 *
 * @author 小常
 * @since 2023-02-15
 */
@Controller
@RequestMapping("/overflow")
public class OverflowListController {
    @Resource
    private IOverflowListService overflowListService;

    @Resource
    private IUserService userService;

    /**
     * 商品报溢主页
     * @return
     */
    @RequestMapping("index")
    public String index(Model model){
        model.addAttribute("overflowNumber",overflowListService.getOverflowNumber());
        return "overflow/overflow";
    }


    @RequestMapping("save")
    @ResponseBody
    public RespBean save(OverflowList overflowList, String goodsJson, Principal principal){
        String userName = principal.getName();
        overflowList.setUserId(userService.findUserByUserName(userName).getId());
        Gson gson = new Gson();
        List<OverflowListGoods> plgList = gson.fromJson(goodsJson,new TypeToken<List<OverflowListGoods>>(){}.getType());
        overflowListService.saveOverflowList(overflowList,plgList);
        return RespBean.success("商品报溢入库成功!");
    }

    @RequestMapping("delete")
    @ResponseBody
    public RespBean deleteGoods(Integer id){
        overflowListService.deleteGoods(id);
        return RespBean.success("报溢单记录删除成功");
    }


    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> overFlowList(OverFlowListQuery overFlowListQuery){
        return overflowListService.overFlowList(overFlowListQuery);
    }
}
