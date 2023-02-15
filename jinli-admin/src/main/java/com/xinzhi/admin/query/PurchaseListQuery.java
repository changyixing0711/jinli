package com.xinzhi.admin.query;

import lombok.Data;

import java.util.List;

/**
 *
 * @author 小常
 * @since 2023-02-03
 */
@Data
public class PurchaseListQuery  extends BaseQuery{

    private String purchaseNumber;
    private Integer supplierId;
    private Integer state;


    private String startDate;
    private String endDate;
    private String goodsName;
    private Integer typeId;
    private List<Integer> typeIds;

    public Integer index;
}
