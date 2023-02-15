package com.xinzhi.admin.service;

import com.xinzhi.admin.pojo.CustomerReturnListGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinzhi.admin.query.CustomerReturnListGoodsQuery;

import java.util.Map;

/**
 * <p>
 * 客户退货单商品表 服务类
 * </p>
 *
 * @author 小常
 * @since 2023-02-15
 */
public interface ICustomerReturnListGoodsService extends IService<CustomerReturnListGoods> {
    Integer getReturnTotalByGoodsId(Integer id);

    Map<String, Object> customerReturnListGoodsList(CustomerReturnListGoodsQuery customerReturnListGoodsQuery);
}
