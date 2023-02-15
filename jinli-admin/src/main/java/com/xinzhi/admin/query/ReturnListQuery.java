package com.xinzhi.admin.query;

import lombok.Data;

/**
 *
 * @author 小常
 * @since 2023-02-03
 */
@Data
public class ReturnListQuery extends BaseQuery{

    private String returnNumber;
    private Integer supplierId;
    private Integer state;
    private String startDate;
    private String endDate;
}
