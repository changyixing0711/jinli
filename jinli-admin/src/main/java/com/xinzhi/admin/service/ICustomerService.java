package com.xinzhi.admin.service;

import com.xinzhi.admin.pojo.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinzhi.admin.query.CustomerQuery;

import java.util.Map;

/**
 * <p>
 * 客户表 服务类
 * </p>
 *
 * @author 小常
 * @since 2023-02-15
 */
public interface ICustomerService extends IService<Customer> {
    Map<String, Object> customerList(CustomerQuery customerQuery);

    void saveCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void deleteCustomer(Integer[] ids);

    Customer findCustomerByName(String name);
}
