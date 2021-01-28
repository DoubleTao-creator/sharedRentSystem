package com.goods.mapper;

import com.goods.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GoodsMapper {
    /**
     *用户查询已购买的商品
     * @param userId
     * @return
     */
    List<Goods> findOwnedGoods(Integer userId);
}
