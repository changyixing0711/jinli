package com.xinzhi.admin.query;

import lombok.Data;

@Data
public class RoleQuery extends BaseQuery {
    /**
     * 根据角色名称
     */
    private String roleName;
}
