package com.xinzhi.admin.service;

import com.xinzhi.admin.pojo.OverflowListGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinzhi.admin.query.OverflowListGoodsQuery;

import java.util.Map;

/**
 * <p>
 * 报溢单商品表 服务类
 * </p>
 *
 * @author 小常
 * @since 2023-02-15
 */
public interface IOverflowListGoodsService extends IService<OverflowListGoods> {
    Map<String, Object> overflowListGoodsList(OverflowListGoodsQuery overflowListGoodsQuery);
}
