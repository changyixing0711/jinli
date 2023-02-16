package com.xinzhi.admin.service;

import com.xinzhi.admin.pojo.OverflowList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinzhi.admin.pojo.OverflowListGoods;
import com.xinzhi.admin.query.OverFlowListQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 报溢单表 服务类
 * </p>
 *
 * @author 小常
 * @since 2023-02-15
 */
public interface IOverflowListService extends IService<OverflowList> {
    String getOverflowNumber();

    void saveOverflowList(OverflowList overflowList, List<OverflowListGoods> plgList);

    Map<String, Object> overFlowList(OverFlowListQuery overFlowListQuery);

    void deleteGoods(Integer id);
}
