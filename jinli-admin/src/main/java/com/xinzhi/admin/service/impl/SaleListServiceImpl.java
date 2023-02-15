package com.xinzhi.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinzhi.admin.pojo.Goods;
import com.xinzhi.admin.pojo.SaleList;
import com.xinzhi.admin.dao.SaleListMapper;
import com.xinzhi.admin.pojo.SaleListGoods;
import com.xinzhi.admin.query.SaleListQuery;
import com.xinzhi.admin.service.IGoodsService;
import com.xinzhi.admin.service.ISaleListGoodsService;
import com.xinzhi.admin.service.ISaleListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinzhi.admin.utils.AssertUtil;
import com.xinzhi.admin.utils.DateUtil;
import com.xinzhi.admin.utils.PageResultUtil;
import com.xinzhi.admin.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 销售单表 服务实现类
 * </p>
 *
 * @author 小常
 * @since 2023-02-15
 */
@Service
public class SaleListServiceImpl extends ServiceImpl<SaleListMapper, SaleList> implements ISaleListService {
    @Resource
    private IGoodsService goodsService;

    @Resource
    private ISaleListGoodsService saleListGoodsService;


    @Override
    public String getNextSaleNumber() {
        try {
            StringBuffer stringBuffer =new StringBuffer();
            stringBuffer.append("XS");
            stringBuffer.append(DateUtil.getCurrentDateStr());
            String saleNumber = this.baseMapper.getNextSaleNumber();
            if(null !=saleNumber){
                stringBuffer.append(StringUtil.formatCode(saleNumber));
            }else{
                stringBuffer.append("0001");
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public void saveSaleList(SaleList saleList, List<SaleListGoods> slgList) {
        AssertUtil.isTrue(!(this.save(saleList)),"记录添加失败!");
        SaleList temp = this.getOne(new QueryWrapper<SaleList>().eq("sale_number",saleList.getSaleNumber()));
        slgList.forEach(slg->{
            slg.setSaleListId(temp.getId());
            Goods goods = goodsService.getById(slg.getGoodsId());
            goods.setInventoryQuantity(goods.getInventoryQuantity()-slg.getNum());
            goods.setState(2);
            AssertUtil.isTrue(!(goodsService.updateById(goods)),"记录添加失败!");
            AssertUtil.isTrue(!(saleListGoodsService.save(slg)),"记录添加失败!");
        });
    }

    @Override
    public Map<String, Object> saleList(SaleListQuery saleListQuery) {
        IPage<SaleList> page = new Page<SaleList>(saleListQuery.getPage(),saleListQuery.getLimit());
        page =  this.baseMapper.saleList(page,saleListQuery);
        return PageResultUtil.getResult(page.getTotal(),page.getRecords());
    }
}
