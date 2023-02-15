package com.xinzhi.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinzhi.admin.pojo.CustomerReturnList;
import com.xinzhi.admin.dao.CustomerReturnListMapper;
import com.xinzhi.admin.pojo.CustomerReturnListGoods;
import com.xinzhi.admin.pojo.Goods;
import com.xinzhi.admin.query.CustomerReturnListQuery;
import com.xinzhi.admin.service.ICustomerReturnListGoodsService;
import com.xinzhi.admin.service.ICustomerReturnListService;
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
 * 客户退货单表 服务实现类
 * </p>
 *
 * @author 小常
 * @since 2023-02-15
 */
@Service
public class CustomerReturnListServiceImpl extends ServiceImpl<CustomerReturnListMapper, CustomerReturnList> implements ICustomerReturnListService {
    @Resource
    private IGoodsService goodsService;

    @Resource
    private ICustomerReturnListGoodsService customerReturnListGoodsService;

    @Override
    public String getNextCustomerReturnNumber() {
        try {
            StringBuffer stringBuffer =new StringBuffer();
            stringBuffer.append("XT");
            stringBuffer.append(DateUtil.getCurrentDateStr());
            String customerReturnNumber = this.baseMapper.getNextCustomerReturnNumber();
            if(null !=customerReturnNumber){
                stringBuffer.append(StringUtil.formatCode(customerReturnNumber));
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
    public void saveCustomerReturnList(CustomerReturnList customerReturnList, List<CustomerReturnListGoods> slgList) {
        AssertUtil.isTrue(!(this.save(customerReturnList)),"记录添加失败!");
        CustomerReturnList temp = this.getOne(new QueryWrapper<CustomerReturnList>().eq("customer_return_number",customerReturnList.getCustomerReturnNumber()));
        slgList.forEach(slg->{
            slg.setCustomerReturnListId(temp.getId());
            Goods goods = goodsService.getById(slg.getGoodsId());
            goods.setInventoryQuantity(goods.getInventoryQuantity()+slg.getNum());
            goods.setState(2);
            AssertUtil.isTrue(!(goodsService.updateById(goods)),"记录添加失败!");
            AssertUtil.isTrue(!(customerReturnListGoodsService.save(slg)),"记录添加失败!");
        });
    }

    @Override
    public Map<String, Object> customerReturnList(CustomerReturnListQuery customerReturnListQuery) {
        IPage<CustomerReturnList> page = new Page<CustomerReturnList>(customerReturnListQuery.getPage(),customerReturnListQuery.getLimit());
        page =  this.baseMapper.customerReturnList(page,customerReturnListQuery);
        return PageResultUtil.getResult(page.getTotal(),page.getRecords());
    }
}
