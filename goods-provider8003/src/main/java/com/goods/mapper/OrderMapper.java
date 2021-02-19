package com.goods.mapper;

import com.goods.entity.Goods;
import com.goods.entity.Installment;
import com.goods.entity.RentToBuy;
import com.goods.entity.ShareRent;
import entity.OrderRecode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

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

    /**
     * 查询以租代售
     * @param id
     * @return
     */
    Installment findInstalmentById(Integer id);

    /**
     *查询先租后买
     * @param id
     * @return
     */
    RentToBuy findRentToBuyById(Integer id);

    /**
     * 查询共享租赁
     * @param id
     * @return
     */
    ShareRent findShareRentById(Integer id);

    /**
     * 更改订单状态
     * @param goodsId 订单id
     * @param status 订单状态
     * @return
     */
    Integer changeGoodsStatus(@Param("goodsId") Integer goodsId,@Param("status") String status);
}
