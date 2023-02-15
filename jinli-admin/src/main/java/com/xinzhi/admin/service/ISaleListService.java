package com.xinzhi.admin.service;

import com.xinzhi.admin.pojo.SaleList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinzhi.admin.pojo.SaleListGoods;
import com.xinzhi.admin.query.SaleListQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 销售单表 服务类
 * </p>
 *
 * @author 小常
 * @since 2023-02-15
 */
public interface ISaleListService extends IService<SaleList> {
    String getNextSaleNumber();

    void saveSaleList(SaleList saleList, List<SaleListGoods> slgList);

    Map<String, Object> saleList(SaleListQuery saleListQuery);
}
