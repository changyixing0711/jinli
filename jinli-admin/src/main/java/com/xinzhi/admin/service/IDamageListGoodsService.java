package com.xinzhi.admin.service;

import com.xinzhi.admin.pojo.DamageListGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinzhi.admin.query.DamageListGoodsQuery;

import java.util.Map;

/**
 * <p>
 * 报损单商品表 服务类
 * </p>
 *
 * @author 小常
 * @since 2023-02-14
 */
public interface IDamageListGoodsService extends IService<DamageListGoods> {
    Map<String, Object> damageListGoodsList(DamageListGoodsQuery damageListGoodsQuery);
}
