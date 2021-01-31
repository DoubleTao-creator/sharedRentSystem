package com.goods.controller;

import com.goods.dto.UserExperienceDTO;
import com.goods.service.GoodsService;
import entity.CommonResult;
import entity.CommonResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
/**
 *@author  xtt
 */
@RestController
public class GoodsController {
    @Autowired
    GoodsService goodsService;
    @PostMapping("/goods/createOrder")
    public CommonResult ExperienceGoods(@RequestBody UserExperienceDTO userExperienceDTO){
        Integer result=goodsService.ExperienceGoods(userExperienceDTO);
        if(result==0){
            return CommonResultVO.error("余额不足!");
        }else if(result==1){
            return CommonResultVO.success(null);
        }else {
            return CommonResultVO.error("发生未知错误");
        }
    }

    /**
     * 用户直接购买商品(未体验）
     * @param cgoodsId
     * @param userId
     * @return
     */
    public CommonResult purchaseGoods(Integer cgoodsId,Integer userId){
        return null;
    }
}
