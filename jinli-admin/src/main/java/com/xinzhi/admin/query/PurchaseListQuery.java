package com.xinzhi.admin.query;

import lombok.Data;

/**
 * 乐字节  踏实教育 用心服务
 *
 * @author 乐字节--老李
 * @version 1.0
 */
@Data
public class PurchaseListQuery  extends BaseQuery{

    private String purchaseNumber;
    private Integer supplierId;
    private Integer state;
    private String startDate;
    private String endDate;
}
