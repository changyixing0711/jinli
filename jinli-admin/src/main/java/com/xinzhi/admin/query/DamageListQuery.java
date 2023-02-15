package com.xinzhi.admin.query;

import lombok.Data;

/**
 *
 * @author 小常
 * @since 2023-02-03
 */
@Data
public class DamageListQuery extends BaseQuery{

    private String startDate;
    private String endDate;
}
