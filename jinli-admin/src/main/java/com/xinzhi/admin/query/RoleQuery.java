package com.xinzhi.admin.query;

import lombok.Data;

/**
 *
 * @author 小常
 * @since 2023-02-03
 */
@Data
public class RoleQuery extends BaseQuery {
    /**
     * 根据角色名称
     */
    private String roleName;
}
