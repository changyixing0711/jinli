package com.xinzhi.admin.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xinzhi.admin.pojo.SaleList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xinzhi.admin.query.SaleListQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 销售单表 Mapper 接口
 * </p>
 *
 * @author 小常
 * @since 2023-02-15
 */
public interface SaleListMapper extends BaseMapper<SaleList> {
    String  getNextSaleNumber();

    IPage<SaleList> saleList(IPage<SaleList> page, @Param("saleListQuery") SaleListQuery saleListQuery);
}
