package com.xinzhi.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinzhi.admin.pojo.DamageList;
import com.xinzhi.admin.dao.DamageListMapper;
import com.xinzhi.admin.pojo.DamageListGoods;
import com.xinzhi.admin.pojo.Goods;
import com.xinzhi.admin.query.DamageListQuery;
import com.xinzhi.admin.service.IDamageListGoodsService;
import com.xinzhi.admin.service.IDamageListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinzhi.admin.service.IGoodsService;
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
 * 报损单表 服务实现类
 * </p>
 *
 * @author 小常
 * @since 2023-02-14
 */
@Service
public class DamageListServiceImpl extends ServiceImpl<DamageListMapper, DamageList> implements IDamageListService {
    @Resource
    private IGoodsService goodsService;

    @Resource
    private IDamageListGoodsService damageListGoodsService;

    @Override
    public String getNextDamageNumber() {
        try {
            StringBuffer stringBuffer =new StringBuffer();
            stringBuffer.append("BS");
            stringBuffer.append(DateUtil.getCurrentDateStr());
            String saleNumber = this.baseMapper.getNextDamageNumber();
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
    public void saveDamageList(DamageList damageList, List<DamageListGoods> plgList) {
        AssertUtil.isTrue(!(this.save(damageList)),"记录添加失败!");
        DamageList temp = this.getOne(new QueryWrapper<DamageList>().eq("damage_number",damageList.getDamageNumber()));
        plgList.forEach(plg->{
            plg.setDamageListId(temp.getId());
            Goods goods = goodsService.getById(plg.getGoodsId());
            goods.setInventoryQuantity(goods.getInventoryQuantity()-plg.getNum());
            goods.setState(2);
            AssertUtil.isTrue(!(goodsService.updateById(goods)),"记录添加失败!");
            AssertUtil.isTrue(!(damageListGoodsService.save(plg)),"记录添加失败!");
        });
    }

    @Override
    public Map<String, Object> damageList(DamageListQuery damageListQuery) {
        IPage<DamageList> page = new Page<DamageList>(damageListQuery.getPage(),damageListQuery.getLimit());
        page =  this.baseMapper.damageList(page,damageListQuery);
        return PageResultUtil.getResult(page.getTotal(),page.getRecords());
    }
}
