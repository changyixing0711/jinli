package com.xinzhi.admin.service;

import com.xinzhi.admin.pojo.ReturnListGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinzhi.admin.query.ReturnListGoodsQuery;

import java.util.Map;

/**
 * <p>
 * 退货单商品表 服务类
 * </p>
 *
 * @author 小常
 * @since 2023-02-15
 */
public interface IReturnListGoodsService extends IService<ReturnListGoods> {
    Map<String, Object> returnListGoodsList(ReturnListGoodsQuery returnListGoodsQuery);
}
