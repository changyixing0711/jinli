package com.xinzhi.admin.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xinzhi.admin.pojo.OverflowList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xinzhi.admin.query.OverFlowListQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 报溢单表 Mapper 接口
 * </p>
 *
 * @author 小常
 * @since 2023-02-15
 */
public interface OverflowListMapper extends BaseMapper<OverflowList> {
    String  getOverflowNumber();

    IPage<OverflowList> overFlowList(IPage<OverflowList> page, @Param("overFlowListQuery") OverFlowListQuery overFlowListQuery);
}
