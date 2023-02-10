package com.xinzhi.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xinzhi.admin.pojo.PurchaseList;
import com.xinzhi.admin.dao.PurchaseListMapper;
import com.xinzhi.admin.service.IPurchaseListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinzhi.admin.utils.DateUtil;
import com.xinzhi.admin.utils.StringUtil;
import org.springframework.stereotype.Service;

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

    @Override
    public String getNextPurchaseNumber() {
        //单号生成
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("JH");
            stringBuffer.append(DateUtil.getCurrentDateStr());
            String purchaseNumber=this.baseMapper.getNextPurchaseNumber();
            if (null != purchaseNumber){
            stringBuffer.append(StringUtil.formatCode(purchaseNumber));
            }else {
            stringBuffer.append("0001");
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
