package com.xinzhi.admin.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xinzhi.admin.pojo.DamageList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xinzhi.admin.query.DamageListQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 报损单表 Mapper 接口
 * </p>
 *
 * @author 小常
 * @since 2023-02-14
 */
public interface DamageListMapper extends BaseMapper<DamageList> {
    String  getNextDamageNumber();

    IPage<DamageList> damageList(IPage<DamageList> page, @Param("damageListQuery") DamageListQuery damageListQuery);
}
