package com.goods.service.impl;

import com.goods.dto.UserExperienceDTO;
import com.goods.service.GoodsService;
/**
 * @author xtt
 */
public class GoodsServiceImpl implements GoodsService{
    @Override
    public void ExperienceGoods(UserExperienceDTO userExperienceDTO) {
        String sellModel=userExperienceDTO.getSellModel();
        if("以租代售".equals(sellModel)){

        }else if("先租后买".equals(sellModel)){

        }else if("共享租赁".equals(sellModel)){
            
        }
    }
}
