package com.xinzhi.admin.dao;

import com.xinzhi.admin.dto.TreeDto;
import com.xinzhi.admin.pojo.GoodsType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 商品类别表 Mapper 接口
 * </p>
 *
 * @author 飞飞
 * @since 2023-02-10
 */
public interface GoodsTypeMapper extends BaseMapper<GoodsType> {

    List<TreeDto> queryAllGoodsTypes();

}
