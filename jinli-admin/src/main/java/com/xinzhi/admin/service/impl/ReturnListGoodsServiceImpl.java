package com.xinzhi.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinzhi.admin.pojo.ReturnListGoods;
import com.xinzhi.admin.dao.ReturnListGoodsMapper;
import com.xinzhi.admin.query.ReturnListGoodsQuery;
import com.xinzhi.admin.service.IReturnListGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinzhi.admin.utils.PageResultUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 退货单商品表 服务实现类
 * </p>
 *
 * @author 小常
 * @since 2023-02-15
 */
@Service
public class ReturnListGoodsServiceImpl extends ServiceImpl<ReturnListGoodsMapper, ReturnListGoods> implements IReturnListGoodsService {
    @Override
    public Map<String, Object> returnListGoodsList(ReturnListGoodsQuery returnListGoodsQuery) {
        IPage<ReturnListGoods> page = new Page<ReturnListGoods>(returnListGoodsQuery.getPage(),returnListGoodsQuery.getLimit());
        QueryWrapper<ReturnListGoods> queryWrapper =new QueryWrapper<ReturnListGoods>();
        if(null != returnListGoodsQuery.getReturnListId()){
            queryWrapper.eq("return_list_id",returnListGoodsQuery.getReturnListId());
        }
        page =  this.baseMapper.selectPage(page,queryWrapper);
        return PageResultUtil.getResult(page.getTotal(),page.getRecords());
    }
}
