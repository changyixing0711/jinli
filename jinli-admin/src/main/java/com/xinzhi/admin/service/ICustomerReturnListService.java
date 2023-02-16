package com.xinzhi.admin.service;

import com.xinzhi.admin.pojo.CustomerReturnList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinzhi.admin.pojo.CustomerReturnListGoods;
import com.xinzhi.admin.query.CustomerReturnListQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户退货单表 服务类
 * </p>
 *
 * @author 小常
 * @since 2023-02-15
 */
public interface ICustomerReturnListService extends IService<CustomerReturnList> {
    String getNextCustomerReturnNumber();

    void saveCustomerReturnList(CustomerReturnList customerReturnList, List<CustomerReturnListGoods> slgList);

    Map<String, Object> customerReturnList(CustomerReturnListQuery customerReturnListQuery);

    void deleteCustomerList(Integer id);
}
