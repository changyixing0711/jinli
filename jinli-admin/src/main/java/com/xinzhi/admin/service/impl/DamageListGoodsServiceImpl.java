package com.xinzhi.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinzhi.admin.pojo.DamageListGoods;
import com.xinzhi.admin.dao.DamageListGoodsMapper;
import com.xinzhi.admin.query.DamageListGoodsQuery;
import com.xinzhi.admin.service.IDamageListGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinzhi.admin.utils.PageResultUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 报损单商品表 服务实现类
 * </p>
 *
 * @author 小常
 * @since 2023-02-14
 */
@Service
public class DamageListGoodsServiceImpl extends ServiceImpl<DamageListGoodsMapper, DamageListGoods> implements IDamageListGoodsService {
    @Override
    public Map<String, Object> damageListGoodsList(DamageListGoodsQuery damageListGoodsQuery) {
        IPage<DamageListGoods> page = new Page<DamageListGoods>(damageListGoodsQuery.getPage(),damageListGoodsQuery.getLimit());
        QueryWrapper<DamageListGoods> queryWrapper =new QueryWrapper<DamageListGoods>();
        if(null != damageListGoodsQuery.getDamageListId()){
            queryWrapper.eq("damage_list_id",damageListGoodsQuery.getDamageListId());
        }
        page =  this.baseMapper.selectPage(page,queryWrapper);
        return PageResultUtil.getResult(page.getTotal(),page.getRecords());
    }
}
