package com.xinzhi.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinzhi.admin.model.CountResultModel;
import com.xinzhi.admin.pojo.Goods;
import com.xinzhi.admin.pojo.SaleList;
import com.xinzhi.admin.dao.SaleListMapper;
import com.xinzhi.admin.pojo.SaleListGoods;
import com.xinzhi.admin.query.SaleListQuery;
import com.xinzhi.admin.service.IGoodsService;
import com.xinzhi.admin.service.IGoodsTypeService;
import com.xinzhi.admin.service.ISaleListGoodsService;
import com.xinzhi.admin.service.ISaleListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinzhi.admin.utils.AssertUtil;
import com.xinzhi.admin.utils.DateUtil;
import com.xinzhi.admin.utils.PageResultUtil;
import com.xinzhi.admin.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Resource
    private IGoodsTypeService goodsTypeService;


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

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateSaleListState(Integer id) {
        SaleList saleList =this.getById(id);
        AssertUtil.isTrue(null == saleList,"待结算的销售单记录不存在!");
        AssertUtil.isTrue(saleList.getState()==1,"该销售单已结算!");
        saleList.setState(1);
        AssertUtil.isTrue(!(this.updateById(saleList)),"销售单结算失败!");
    }


    @Override
    public Map<String, Object> countSale(SaleListQuery saleListQuery) {
        /**
         * 分页查询
         *   查总数
         *   查当前页列表
         */
        if(null !=saleListQuery.getTypeId()){
            List<Integer> typeIds= goodsTypeService.queryAllSubTypeIdsByTypeId(saleListQuery.getTypeId());
            saleListQuery.setTypeIds(typeIds);
        }
        /**
         *  page
         *    1-->0
         *    2-->10
         *    3-->20
         */
        saleListQuery.setIndex((saleListQuery.getPage()-1)*saleListQuery.getLimit());
        Long count  = this.baseMapper.countSaleTotal(saleListQuery);
        List<CountResultModel> list =this.baseMapper.saleListQueryList(saleListQuery);
        return PageResultUtil.getResult(count,list);
    }

    @Override
    public List<Map<String, Object>> countDaySale(String begin, String end) {
        return this.baseMapper.countDaySale(begin,end);
    }

    @Override
    public List<Map<String, Object>> countMonthSale(String begin, String end) {
        return this.baseMapper.countMonthSale(begin,end);
    }

    @Override
    public void deleteCustomerList(Integer id) {
        SaleList saleList = this.getById(id);
        AssertUtil.isTrue(saleList.getState()==0,"订单未付款无法删除");
        AssertUtil.isTrue(!(saleListGoodsService.remove(new QueryWrapper<SaleListGoods>().eq("sale_list_id",id))),"记录删除失败");
        AssertUtil.isTrue(!(this.removeById(id)),"记录删除失败");
    }
}
