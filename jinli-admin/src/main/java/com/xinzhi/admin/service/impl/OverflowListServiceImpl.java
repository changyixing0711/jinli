package com.xinzhi.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinzhi.admin.pojo.Goods;
import com.xinzhi.admin.pojo.OverflowList;
import com.xinzhi.admin.dao.OverflowListMapper;
import com.xinzhi.admin.pojo.OverflowListGoods;
import com.xinzhi.admin.query.OverFlowListQuery;
import com.xinzhi.admin.service.IGoodsService;
import com.xinzhi.admin.service.IOverflowListGoodsService;
import com.xinzhi.admin.service.IOverflowListService;
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
 * 报溢单表 服务实现类
 * </p>
 *
 * @author 小常
 * @since 2023-02-15
 */
@Service
public class OverflowListServiceImpl extends ServiceImpl<OverflowListMapper, OverflowList> implements IOverflowListService {
    @Resource
    private IGoodsService goodsService;

    @Resource
    private IOverflowListGoodsService overflowListGoodsService;

    @Override
    public String getOverflowNumber() {
        try {
            StringBuffer stringBuffer =new StringBuffer();
            stringBuffer.append("BY");
            stringBuffer.append(DateUtil.getCurrentDateStr());
            String saleNumber = this.baseMapper.getOverflowNumber();
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
    public void saveOverflowList(OverflowList overflowList, List<OverflowListGoods> plgList) {
        AssertUtil.isTrue(!(this.save(overflowList)),"记录添加失败!");
        OverflowList temp = this.getOne(new QueryWrapper<OverflowList>().eq("overflow_number",overflowList.getOverflowNumber()));
        plgList.forEach(plg->{
            plg.setOverflowListId(temp.getId());
            Goods goods = goodsService.getById(plg.getGoodsId());
            goods.setInventoryQuantity(goods.getInventoryQuantity()+plg.getNum());
            goods.setState(2);
            AssertUtil.isTrue(!(goodsService.updateById(goods)),"记录添加失败!");
            AssertUtil.isTrue(!(overflowListGoodsService.save(plg)),"记录添加失败!");
        });
    }

    @Override
    public Map<String, Object> overFlowList(OverFlowListQuery overFlowListQuery) {
        IPage<OverflowList> page = new Page<OverflowList>(overFlowListQuery.getPage(),overFlowListQuery.getLimit());
        page =  this.baseMapper.overFlowList(page,overFlowListQuery);
        return PageResultUtil.getResult(page.getTotal(),page.getRecords());
    }
}
