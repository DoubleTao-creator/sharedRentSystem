package com.xyc.mapper;

import com.xyc.dto.OrderRecordDTO;
import com.xyc.pojo.OrderRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderRecordMapper {

    public int add(OrderRecordDTO order);

    public List<OrderRecord> queryByGoodsId(@Param("goodsId")Integer id);


}


