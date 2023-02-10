package com.xinzhi.admin.dao;

import com.xinzhi.admin.pojo.PurchaseList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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
}
