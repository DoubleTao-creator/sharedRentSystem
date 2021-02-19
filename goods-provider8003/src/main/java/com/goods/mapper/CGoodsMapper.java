package com.goods.mapper;

import com.goods.entity.Cgoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CGoodsMapper {
    /**
     * 根据ID查询商品信息
     * @param cgoodsId
     * @return
     */
    Cgoods getCgoodsById(Integer cgoodsId);

    /**
     * 变更商品类库存
     * @param cGoodsId 商品类ID
     * @param repertoryToChange 正数为增加库存，负数为减少库存
     * @return
     */
    Integer changeCGoodsRepertory(@Param("cGoodsId") Integer cGoodsId,@Param("repertory") Integer repertoryToChange);
}
