package com.xinzhi.admin.service;

import com.xinzhi.admin.pojo.SaleListGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinzhi.admin.query.saleListGoodsQuery;

import java.util.Map;

/**
 * <p>
 * 销售单商品表 服务类
 * </p>
 *
 * @author 小常
 * @since 2023-02-15
 */
public interface ISaleListGoodsService extends IService<SaleListGoods> {
    Integer getSaleTotalByGoodsId(Integer id);

    Map<String, Object> saleListGoodsList(saleListGoodsQuery saleListGoodsQuery);
}
