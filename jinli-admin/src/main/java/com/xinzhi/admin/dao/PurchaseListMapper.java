package com.xinzhi.admin.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xinzhi.admin.pojo.PurchaseList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xinzhi.admin.query.PurchaseListQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 进货单 Mapper 接口
 * </p>
 *
 * @author 小常
 * @since 2023-02-09
 */
public interface PurchaseListMapper extends BaseMapper<PurchaseList> {

    String getNextPurchaseNumber();

    IPage<PurchaseList> purchaseList(IPage<PurchaseList> page, @Param("purchaseListQuery") PurchaseListQuery purchaseListQuery);
}
