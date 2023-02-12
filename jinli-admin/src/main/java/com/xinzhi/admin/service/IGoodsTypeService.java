package com.xinzhi.admin.service;

import com.xinzhi.admin.dto.TreeDto;
import com.xinzhi.admin.pojo.GoodsType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品类别表 服务类
 * </p>
 *
 * @author 飞飞
 * @since 2023-02-10
 */
public interface IGoodsTypeService extends IService<GoodsType> {

    List<TreeDto> queryAllGoodsTypes(Integer typeId);


    List<Integer> queryAllSubTypeIdsByTypeId(Integer typeId);

    Map<String, Object> goodsTypeList();


    void saveGoodsType(GoodsType goodsType);

    void deleteGoodsType(Integer id);
}
