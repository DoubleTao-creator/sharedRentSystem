package com.goods.controller;

import com.goods.dto.UserExperienceDTO;
import com.goods.mapper.CGoodsMapper;
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
        Integer result=goodsService.purchaseGoods(cgoodsId, userId);
        if(result==0){
            //余额不足
            return CommonResultVO.error("余额不足!");
        }else if(result==1){
            //购买成功
            return CommonResultVO.success(null);
        }else {
            //发生错误,购买失败
            return CommonResultVO.error("发生错误!");
        }
    }

    /**
     * 续租以租代售订单
     * @param goodsId
     * @param userId
     * @return
     */
    public CommonResult rerentGoods(Integer goodsId,Integer userId){
        Integer result=goodsService.rerentGoods(goodsId, userId);
        if(result==2){
            return CommonResultVO.error("商品已无需续租");
        }else if(result==0){
            return CommonResultVO.error("余额不足!");
        }else if(result==1){
            return CommonResultVO.success(null);
        }else {
            return CommonResultVO.error("续租失败");
        }
    }

    /**
     * 购买先租后买订单
     * @param goodsId
     * @param userId
     * @return
     */
    public CommonResult purchaseRentToBuy(Integer goodsId,Integer userId){
        Integer result=goodsService.purchaseRentToBuy(goodsId, userId);
        if(result==0){
            return CommonResultVO.error("余额不足!");
        }else{
            return CommonResultVO.success(null);
        }
    }

    /**
     * 退租
     * @param goodsId
     * @param userId
     * @return
     */
    public CommonResult refundRent(Integer goodsId,Integer userId){
        Integer result=goodsService.refundRent(goodsId, userId);
        if(result==1){
            System.out.println("userId:"+userId+"退租成功");
            return CommonResultVO.success(null);
        }else {
            System.out.println("userId:"+userId+"退租失败");
            return CommonResultVO.error("退租失败");
        }
    }
}
