package com.xyc.mapper;

import com.xyc.dto.CGoodsAddDTO;
import com.xyc.pojo.CGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CGoodsMapper {

    public CGoods queryById(@Param("id") int id);

    public int add(CGoods cGoods);

    public List<CGoods> queryAll();

    public List<CGoods> queryBySellerId(@Param("sellerId") int sellerId);

    public List<CGoods> queryByInfo(@Param("info") String info);
    //select DISTINCT c.* from cgoods c,seller s WHERE
    //    c.name like '%c%' or
    //    c.info like '%c%' or
    //    c.sellerId in(
    //            SELECT s.id from seller WHERE s.name like '%c%'
    //    )

    public List<CGoods> queryByTypeId(@Param("typeId") int typeId);

    public int modify(CGoods cGoods);

    public int getSellModel(@Param("flag") String flag);


}
