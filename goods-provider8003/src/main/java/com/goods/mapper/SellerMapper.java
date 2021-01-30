package com.goods.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author xtt
 * @date 2021/1/30
 */
@Mapper
@Component
public interface SellerMapper {
    /**
     * 变更商家余额
     * @param sellerId 商家ID
     * @param balanceToChange 变更余额，正数为加，负数为减
     * @return
     */
    Integer changeUserbalance(@Param("sellerId")Integer sellerId, @Param("balanceToChange") Double balanceToChange);
}
