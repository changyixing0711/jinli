package com.xinzhi.admin.service;

import com.xinzhi.admin.pojo.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinzhi.admin.query.GoodsQuery;

import java.util.Map;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author 飞飞
 * @since 2023-02-10
 */
public interface IGoodsService extends IService<Goods> {

    Map<String, Object> goodsList(GoodsQuery goodsQuery);

    String genGoodsCode();

    void saveGoods(Goods goods);

    void updateGoods(Goods goods);

    void deleteGoods(Integer id);

    void updateStock(Goods goods);

    void deleteStock(Integer id);

    Goods getGoodsInfoById(Integer gid);

    Map<String, Object> stockList(GoodsQuery goodsQuery);

}
