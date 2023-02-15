package com.xinzhi.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinzhi.admin.pojo.Goods;
import com.xinzhi.admin.pojo.PurchaseList;
import com.xinzhi.admin.dao.PurchaseListMapper;
import com.xinzhi.admin.pojo.PurchaseListGoods;
import com.xinzhi.admin.query.PurchaseListQuery;
import com.xinzhi.admin.service.IGoodsService;
import com.xinzhi.admin.service.IPurchaseListGoodsService;
import com.xinzhi.admin.service.IPurchaseListService;
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
 * 进货单 服务实现类
 * </p>
 *
 * @author 小常
 * @since 2023-02-09
 */
@Service
public class PurchaseListServiceImpl extends ServiceImpl<PurchaseListMapper, PurchaseList> implements IPurchaseListService {


    @Resource
    private IPurchaseListGoodsService purchaseListGoodsService;

    @Resource
    private IGoodsService goodsService;

    @Override
    public String getNextPurchaseNumber() {
        // JH20210101000X
        try {
            StringBuffer stringBuffer =new StringBuffer();
            stringBuffer.append("JH");
            stringBuffer.append(DateUtil.getCurrentDateStr());
            String purchaseNumber = this.baseMapper.getNextPurchaseNumber();
            if(null !=purchaseNumber){
                stringBuffer.append(StringUtil.formatCode(purchaseNumber));
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
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void savePurchaseList(PurchaseList purchaseList, List<PurchaseListGoods> plgList) {
        AssertUtil.isTrue(!(this.save(purchaseList)),"记录添加失败!");
        PurchaseList temp = this.getOne(new QueryWrapper<PurchaseList>().eq("purchase_number",purchaseList.getPurchaseNumber()));
        plgList.forEach(plg->{
            plg.setPurchaseListId(temp.getId());

            Goods goods = goodsService.getById(plg.getGoodsId());
            goods.setInventoryQuantity(goods.getInventoryQuantity()+plg.getNum());
            goods.setLastPurchasingPrice(plg.getPrice());
            goods.setState(2);
            goodsService.updateById(goods);
        });
        AssertUtil.isTrue(!(purchaseListGoodsService.saveBatch(plgList)),"记录添加失败!");
    }

    @Override
    public Map<String, Object> purchaseList(PurchaseListQuery purchaseListQuery) {
        IPage<PurchaseList> page = new Page<PurchaseList>(purchaseListQuery.getPage(),purchaseListQuery.getLimit());
        page =  this.baseMapper.purchaseList(page,purchaseListQuery);
        return PageResultUtil.getResult(page.getTotal(),page.getRecords());
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deletePurchaseList(Integer id) {
        /**
         * 1.进货单商品记录删除
         * 2.进货单记录删除
         */
        AssertUtil.isTrue(!(purchaseListGoodsService.remove(new QueryWrapper<PurchaseListGoods>().eq("purchase_list_id",id))),
                "记录删除失败!");
        AssertUtil.isTrue(!(this.removeById(id)),"记录删除失败!");


    }
}
