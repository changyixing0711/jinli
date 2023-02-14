package com.xinzhi.admin.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xinzhi.admin.pojo.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xinzhi.admin.query.GoodsQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author 飞飞
 * @since 2023-02-10
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    IPage<Goods>  queryGoodsByParams(IPage<Goods> page, @Param("goodsQuery") GoodsQuery goodsQuery);

    Goods getGoodsInfoById(Integer gid);
}
