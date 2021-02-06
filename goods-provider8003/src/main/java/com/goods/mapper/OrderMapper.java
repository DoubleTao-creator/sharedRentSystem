package com.goods.mapper;

import com.goods.entity.Goods;
import com.goods.entity.Installment;
import com.goods.entity.RentToBuy;
import com.goods.entity.ShareRent;
import entity.OrderRecode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

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

    /**
     * 查询记录
     * @param userId
     * @return
     */
    List<entity.OrderRecode> findRecode(Integer userId);
    String findModel(Integer modelId);

    /**
     * 按用户id查询订单
     * @param userId
     * @return
     */
    List<Goods> findGoodsByUserId(@Param("userId") Integer userId);
    Installment findInstalmentById(Integer id);
    RentToBuy findRentToBuyById(Integer id);
    ShareRent findShareRentById(Integer id);
}
