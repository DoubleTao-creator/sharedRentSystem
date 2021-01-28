package com.goods.mapper;

import com.goods.entity.Cgoods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CGoodsMapper {
    /**
     * 根据ID查询商品信息
     * @param cgoodsId
     * @return
     */
    Cgoods getCgoodsById(Integer cgoodsId);
}
