package com.xinzhi.admin.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xinzhi.admin.pojo.CustomerReturnList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xinzhi.admin.query.CustomerReturnListQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 客户退货单表 Mapper 接口
 * </p>
 *
 * @author 小常
 * @since 2023-02-15
 */
public interface CustomerReturnListMapper extends BaseMapper<CustomerReturnList> {
    String  getNextCustomerReturnNumber();

    IPage<CustomerReturnList> customerReturnList(IPage<CustomerReturnList> page, @Param("customerReturnListQuery") CustomerReturnListQuery customerReturnListQuery);
}
