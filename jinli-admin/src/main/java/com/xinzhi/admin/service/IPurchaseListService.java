package com.xinzhi.admin.service;

import com.xinzhi.admin.pojo.PurchaseList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinzhi.admin.pojo.PurchaseListGoods;
import com.xinzhi.admin.query.PurchaseListQuery;

import java.util.List;
import java.util.Map;

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

    void savePurchaseList(PurchaseList purchaseList, List<PurchaseListGoods> plgList);

    Map<String, Object> purchaseList(PurchaseListQuery purchaseListQuery);

    void deletePurchaseList(Integer id);

    void updatePurchaseListState(Integer pid);

    Map<String, Object> countPurchase(PurchaseListQuery purchaseListQuery);
}
