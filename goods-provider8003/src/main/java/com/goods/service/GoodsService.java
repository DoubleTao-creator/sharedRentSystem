package com.goods.service;

import com.goods.dto.UserExperienceDTO;
import org.springframework.stereotype.Service;

/**
 * @author xtt
 * @date 2021/1/30
 */
@Service
public interface GoodsService {
    /**
     * 创建体验订单
     * @param userExperienceDTO
     * @return
     */
    Integer ExperienceGoods(UserExperienceDTO userExperienceDTO);

    /**
     * 用户购买商品（未体验）
     * @param cgoodsId
     * @param userId
     * @return
     */
    Integer purchaseGoods(Integer cgoodsId,Integer userId);

    /**
     * 续租以租代售订单
     * @param goodsId
     * @param userId
     * @return
     */
    Integer rerentGoods(Integer goodsId,Integer userId);

    /**
     * 购买先租后买
     * @param goodsId
     * @param userId
     * @return
     */
    Integer purchaseRentToBuy(Integer goodsId,Integer userId);

    /**
     * 退租（退商品不退钱）
     * @param goodsId
     * @param userId
     * @return
     */
    Integer refundRent(Integer goodsId,Integer userId);
}
