package com.xinzhi.admin.query;

import lombok.Data;

/**
 *
 * @author 小常
 * @since 2023-02-03
 */
@Data
public class SaleListQuery extends BaseQuery{

    private String saleNumber;
    private Integer customerId;
    private Integer state;
    private String startDate;
    private String endDate;
}
