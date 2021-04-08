package com.xyc.mapper;

import com.xyc.pojo.CGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CGoodsMapper {

    public List<CGoods> queryBySellModel(@Param("flag") String flag);

    public List<CGoods> queryBySeller(@Param("sellerId")Integer sellerId);

    public int updateStatusToFrozen(@Param("cgoodsId")Integer cgoodsId);

}
