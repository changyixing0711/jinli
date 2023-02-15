package com.xinzhi.admin.controller;

import com.xinzhi.admin.model.GoodsModel;
import com.xinzhi.admin.pojo.Goods;
import com.xinzhi.admin.query.GoodsQuery;
import com.xinzhi.admin.service.IGoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/common")
public class CommonController {
    @Resource
    private IGoodsService goodsService;

    /**
     * 添加商品*选择商品页
     * @return
     */
    @RequestMapping("toSelectGoodsPage")
    public String toSelectGoodsPage(){
        return "common/goods";
    }

    @RequestMapping("toAddGoodsInfoPage")
    public String toAddGoodsIfoPage(Integer gid, Model model){
        model.addAttribute("goods",goodsService.getGoodsInfoById(gid));
        return "common/goods_add_update";
    }

    /**
     * 修改商品-商品信息修改页(单价、进货数量)
     * @param goodsModel
     * @param model
     * @return
     */
    @RequestMapping("toUpdateGoodsInfoPage")
    public String toUpdateGoodsInfoPage(GoodsModel goodsModel, Model model){
        Goods goods =goodsService.getGoodsInfoById(goodsModel.getId());
        goodsModel.setCode(goods.getCode());
        goodsModel.setModel(goods.getModel());
        goodsModel.setName(goods.getName());
        goodsModel.setUnit(goods.getUnitName());
        goodsModel.setTypeId(goods.getTypeId());
        goodsModel.setTypeName(goods.getTypeName());
        goodsModel.setLastPurchasingPrice(goods.getLastPurchasingPrice());
        goodsModel.setInventoryQuantity(goods.getInventoryQuantity());
        model.addAttribute("goods",goodsModel);
        model.addAttribute("flag",1);
        return "common/goods_add_update";
    }

    /**
     * 当前库存页
     * @return
     */
    @RequestMapping("toGoodsStockPage")
    public String toGoodsStockPage() {
        return "common/stock_search";
    }

    @RequestMapping("stockList")
    @ResponseBody
    public Map<String,Object> stockLick(GoodsQuery goodsQuery){
        return goodsService.stockList(goodsQuery);
    }


    /**
     * 商品报损|报溢查询页
     * @return
     */
    @RequestMapping("toDamageOverflowSearchPage")
    public String toDamageOverflowSearchPage(){
        return "common/damage_overflow_search";
    }


    /**
     * 库存报警页
     * @return
     */
    @RequestMapping("alarmPage")
    public String alarmPage(){
        return "common/alarm";
    }


    /**
     * 库存报警查询接口
     * @param goodsQuery
     * @return
     */
    @RequestMapping("listAlarm")
    @ResponseBody
    public Map<String,Object> listAlarm(GoodsQuery goodsQuery){
        goodsQuery.setType(3);
        return goodsService.goodsList(goodsQuery);
    }

}
