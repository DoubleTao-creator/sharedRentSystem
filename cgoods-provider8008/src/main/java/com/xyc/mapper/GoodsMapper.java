package com.xyc.mapper;

import com.xyc.dto.GoodsShowDTO;
import com.xyc.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GoodsMapper {

    public List<GoodsShowDTO> getGoodsByCGoodsId(@Param("cGoodsId") int cGoodsId);

}
