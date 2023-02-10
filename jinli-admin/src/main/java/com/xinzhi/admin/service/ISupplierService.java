package com.xinzhi.admin.service;

import com.xinzhi.admin.pojo.Supplier;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinzhi.admin.query.SupplierQuery;

import java.util.Map;

/**
 * <p>
 * 供应商表 服务类
 * </p>
 *
 * @author 小常
 * @since 2023-02-10
 */
public interface ISupplierService extends IService<Supplier> {

    Map<String, Object> supplierList(SupplierQuery supplierQuery);

    Supplier findSupplierByName(String name);

    void savesupplier(Supplier supplier);

    void updatesupplier(Supplier supplier);

    void deleteSupplier(Integer[] ids);
}
