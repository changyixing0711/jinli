package com.xinzhi.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinzhi.admin.pojo.SaleListGoods;
import com.xinzhi.admin.dao.SaleListGoodsMapper;
import com.xinzhi.admin.query.saleListGoodsQuery;
import com.xinzhi.admin.service.ISaleListGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinzhi.admin.utils.PageResultUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 销售单商品表 服务实现类
 * </p>
 *
 * @author 小常
 * @since 2023-02-15
 */
@Service
public class SaleListGoodsServiceImpl extends ServiceImpl<SaleListGoodsMapper, SaleListGoods> implements ISaleListGoodsService {
    @Override
    public Integer getSaleTotalByGoodsId(Integer id) {
        SaleListGoods saleListGoods = this.getOne(new QueryWrapper<SaleListGoods>().select("sum(num) as num").eq("goods_id",id));
        return null == saleListGoods?0:saleListGoods.getNum();
    }


    @Override
    public Map<String, Object> saleListGoodsList(saleListGoodsQuery saleListGoodsQuery) {
        IPage<SaleListGoods> page = new Page<SaleListGoods>(saleListGoodsQuery.getPage(),saleListGoodsQuery.getLimit());
        QueryWrapper<SaleListGoods> queryWrapper =new QueryWrapper<SaleListGoods>();
        if(null != saleListGoodsQuery.getSaleListId()){
            queryWrapper.eq("sale_list_id",saleListGoodsQuery.getSaleListId());
        }
        page =  this.baseMapper.selectPage(page,queryWrapper);
        return PageResultUtil.getResult(page.getTotal(),page.getRecords());
    }
}
