package com.goods.mapper;

import entity.OrderRecode;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author xtt
 */
@Mapper
@Component
public interface OrderMapper {
    /**
     * 添加付款记录
     * @param orderRecode
     * @return
     */
    Integer addOrderRecode(OrderRecode orderRecode);
}
