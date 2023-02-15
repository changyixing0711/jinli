package com.xinzhi.admin.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xinzhi.admin.pojo.ReturnList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xinzhi.admin.query.ReturnListQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 退货单表 Mapper 接口
 * </p>
 *
 * @author 小常
 * @since 2023-02-15
 */
public interface ReturnListMapper extends BaseMapper<ReturnList> {
    String  getNextReturnNumber();

    IPage<ReturnList> returnList(IPage<ReturnList> page, @Param("returnListQuery") ReturnListQuery returnListQuery);
}
