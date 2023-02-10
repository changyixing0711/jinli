package com.xinzhi.admin.service;

import com.xinzhi.admin.pojo.PurchaseList;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 进货单 服务类
 * </p>
 *
 * @author 小常
 * @since 2023-02-09
 */
public interface IPurchaseListService extends IService<PurchaseList> {

    String getNextPurchaseNumber();
}
