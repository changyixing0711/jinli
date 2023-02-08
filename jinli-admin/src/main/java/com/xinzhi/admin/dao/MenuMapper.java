package com.xinzhi.admin.dao;

import com.xinzhi.admin.dto.TreeDto;
import com.xinzhi.admin.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author 小常
 * @since 2023-02-04
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<TreeDto> queryAllMenus();

}
