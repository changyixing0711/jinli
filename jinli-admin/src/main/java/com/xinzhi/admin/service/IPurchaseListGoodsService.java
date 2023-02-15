package com.xinzhi.admin.service;

import com.xinzhi.admin.pojo.PurchaseListGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinzhi.admin.query.PurchaseListGoodsQuery;

import java.util.Map;

/**
 * <p>
 * 进货单商品表 服务类
 * </p>
 *
 * @author 小常
 * @since 2023-02-09
 */
public interface IPurchaseListGoodsService extends IService<PurchaseListGoods> {
    Map<String, Object> purchaseListGoodsList(PurchaseListGoodsQuery purchaseListGoodsQuery);
}
