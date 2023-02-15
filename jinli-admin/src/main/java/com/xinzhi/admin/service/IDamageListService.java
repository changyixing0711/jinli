package com.xinzhi.admin.service;

import com.xinzhi.admin.pojo.DamageList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinzhi.admin.pojo.DamageListGoods;
import com.xinzhi.admin.query.DamageListQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 报损单表 服务类
 * </p>
 *
 * @author 小常
 * @since 2023-02-14
 */
public interface IDamageListService extends IService<DamageList> {
    String getNextDamageNumber();

    void saveDamageList(DamageList damageList, List<DamageListGoods> plgList);

    Map<String, Object> damageList(DamageListQuery damageListQuery);
}
