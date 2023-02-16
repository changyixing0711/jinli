package com.xinzhi.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinzhi.admin.pojo.Goods;
import com.xinzhi.admin.pojo.ReturnList;
import com.xinzhi.admin.dao.ReturnListMapper;
import com.xinzhi.admin.pojo.ReturnListGoods;
import com.xinzhi.admin.query.ReturnListQuery;
import com.xinzhi.admin.service.IGoodsService;
import com.xinzhi.admin.service.IReturnListGoodsService;
import com.xinzhi.admin.service.IReturnListService;
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
 * 退货单表 服务实现类
 * </p>
 *
 * @author 小常
 * @since 2023-02-15
 */
@Service
public class ReturnListServiceImpl extends ServiceImpl<ReturnListMapper, ReturnList> implements IReturnListService {
    @Resource
    private IReturnListGoodsService returnListGoodsService;

    @Resource
    private IGoodsService goodsService;

    @Override
    public String getNextReturnNumber() {
        // TH20210101000X
        try {
            StringBuffer stringBuffer =new StringBuffer();
            stringBuffer.append("TH");
            stringBuffer.append(DateUtil.getCurrentDateStr());
            String returnNumber = this.baseMapper.getNextReturnNumber();
            if(null !=returnNumber){
                stringBuffer.append(StringUtil.formatCode(returnNumber));
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
    public void saveReturnList(ReturnList returnList, List<ReturnListGoods> rlgList) {
        AssertUtil.isTrue(!(this.save(returnList)),"记录添加失败!");
        ReturnList  temp = this.getOne(new QueryWrapper<ReturnList>().eq("return_number",returnList.getReturnNumber()));
        rlgList.forEach(rlg->{
            rlg.setReturnListId(temp.getId());

            Goods goods =goodsService.getById(rlg.getGoodsId());
            goods.setInventoryQuantity(goods.getInventoryQuantity()-rlg.getNum());
            goods.setState(2);
            goodsService.updateById(goods);

        });
        AssertUtil.isTrue(!(returnListGoodsService.saveBatch(rlgList)),"记录添加失败!");
    }

    @Override
    public Map<String, Object> returnList(ReturnListQuery returnListQuery) {
        IPage<ReturnList> page = new Page<ReturnList>(returnListQuery.getPage(),returnListQuery.getLimit());
        page =  this.baseMapper.returnList(page,returnListQuery);
        return PageResultUtil.getResult(page.getTotal(),page.getRecords());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deleteReturnList(Integer id) {
        /**
         * 1.退货单商品记录删除
         * 2.退货单记录删除
         */
        AssertUtil.isTrue(!(returnListGoodsService.remove(new QueryWrapper<ReturnListGoods>().eq("return_list_id",id))),
                "记录删除失败!");
        AssertUtil.isTrue(!(this.removeById(id)),"记录删除失败!");
    }
}
