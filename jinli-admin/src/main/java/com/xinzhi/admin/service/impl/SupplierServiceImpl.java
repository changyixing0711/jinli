package com.xinzhi.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinzhi.admin.pojo.Supplier;
import com.xinzhi.admin.dao.SupplierMapper;
import com.xinzhi.admin.query.SupplierQuery;
import com.xinzhi.admin.service.ISupplierService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinzhi.admin.utils.AssertUtil;
import com.xinzhi.admin.utils.PageResultUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 供应商表 服务实现类
 * </p>
 *
 * @author 小常
 * @since 2023-02-10
 */
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements ISupplierService {

    @Override
    public Map<String, Object> supplierList(SupplierQuery supplierQuery) {
        Page<Supplier> page = new Page<>(supplierQuery.getPage(),supplierQuery.getLimit());
        QueryWrapper<Supplier> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_del",0);
        if (StringUtils.isNotBlank(supplierQuery.getSupplierName())){
            queryWrapper.like("name",supplierQuery.getSupplierName());
        }
        page=this.baseMapper.selectPage(page,queryWrapper);
        return PageResultUtil.getResult(page.getTotal(),page.getRecords());
    }

    @Override
    public Supplier findSupplierByName(String name) {
        return this.getOne(new QueryWrapper<Supplier>().eq("is_del",0).eq("name",name));
    }

    @Override
    public void savesupplier(Supplier supplier) {
        /**
         * 供应商名称非空  联系人 联系电话
         * is_del 0 有效
         * 供应商不能重复
         */
    checkParams(supplier.getName(),supplier.getContact(),supplier.getNumber());
    AssertUtil.isTrue(null !=this.findSupplierByName(supplier.getName()),"该供应商已存在");
    supplier.setIsDel(0);
    AssertUtil.isTrue(!(this.save(supplier)),"添加失败!");
    }

    private void checkParams(String name, String contact, String number) {
        AssertUtil.isTrue(StringUtils.isBlank(name),"请输入供货商名称!");
        AssertUtil.isTrue(StringUtils.isBlank(contact),"请输入联系人");
        AssertUtil.isTrue(StringUtils.isBlank(number),"请输入联系人的联系电话");
    }

    @Override
    public void updatesupplier(Supplier supplier) {
    AssertUtil.isTrue(null == this.getById(supplier.getId()),"请选择供应商记录!");
    checkParams(supplier.getName(),supplier.getContact(),supplier.getNumber());
    Supplier temp =this.findSupplierByName(supplier.getName());
    AssertUtil.isTrue(null != temp && !(temp.getId().equals(supplier.getId())),"供应商已存在!!");
    AssertUtil.isTrue(!(this.updateById(supplier)),"记录更新失败");
    }

    @Override
    public void deleteSupplier(Integer[] ids) {
        AssertUtil.isTrue(null==ids || ids.length == 0,"请选择你要删除的id");
        List<Supplier> supplierList = new ArrayList<Supplier>();
        for (Integer id : ids) {
            Supplier temp=this.getById(id);
            temp.setIsDel(1);
            supplierList.add(temp);
        }
        AssertUtil.isTrue(!(this.updateBatchById(supplierList)),"记录删除失败!");;
    }

}
