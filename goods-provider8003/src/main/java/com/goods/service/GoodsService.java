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
    Integer purchaseGoods(Integer cgoodsId,Integer userId);
}
