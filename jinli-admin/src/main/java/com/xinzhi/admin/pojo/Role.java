package com.xinzhi.admin.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author 小常
 * @since 2023-02-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_role")
@ApiModel(value="Role对象", description="角色表")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    /**
     * 角色主键
     */
    private Integer id;

    @ApiModelProperty(value = "备注")
    /**
     * 备注
     */
    private String bz;

    @ApiModelProperty(value = "角色名")
    /**
     * 角色名
     */
    private String name;

    @ApiModelProperty(value = "描述")
    /**
     * 描述
     */
    private String remarks;

    @ApiModelProperty(value = "是否删除")
    /**
     * 是否删除
     */
    private Integer isDel;


}
