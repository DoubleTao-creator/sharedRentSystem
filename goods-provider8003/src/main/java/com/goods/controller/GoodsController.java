package com.goods.controller;

import com.goods.dto.UserExperienceDTO;
import com.goods.service.GoodsService;
import com.goods.service.OrderService;
import com.goods.vo.OrderResultVO;
import com.goods.vo.RecodeVO;
import entity.CommonResult;
import entity.CommonResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 *@author  xtt
 */
@RestController
public class GoodsController {
    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderService orderService;
    /**
     * 用户下体验订单
     * @param userExperienceDTO
     * @return
     */
    @PostMapping("/goods/createOrder")
    public CommonResult ExperienceGoods(@RequestBody UserExperienceDTO userExperienceDTO){
        Integer result=goodsService.ExperienceGoods(userExperienceDTO);
        if(result==0){
            return CommonResultVO.error("余额不足!");
        }else if(result==1){
            return CommonResultVO.success(null);
        }else if(result==2){
            return CommonResultVO.error("信誉分低于60无法下单");
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
    @PostMapping("/goods/purchaseGoods")
    public CommonResult purchaseGoods(@RequestParam("cgoodsId") Integer cgoodsId, @RequestParam("userId") Integer userId){
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
    @PostMapping("/goods/rerentGoods")
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
    @PostMapping("/goods/purchaseRentToBuy")
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
    @PostMapping("/goods/refundRent")
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

    /**
     * 结算共享租赁订单
     * @param goodsId
     * @param userId
     * @return
     */
    @PostMapping("/goods/settleShareRent")
    public CommonResult settltShareRent(Integer goodsId,Integer userId){
        Integer result=goodsService.settleShareRent(goodsId, userId);
        if(result==0){
            return CommonResultVO.error("余额不足");
        }
        //结算成功
        return CommonResultVO.success(null);
    }

    /**
     * 用户查询自己已购买的订单
     * @param userId
     * @return
     */
    @GetMapping("/goods/findOwnedGoods")
    public CommonResult findOwnedGoods(Integer userId){
        return CommonResultVO.success(orderService.findOwnedGoods(userId));
    }

    /**
     * 查询操作记录
     * @param userId
     * @return
     */
    @GetMapping("/goods/getRecode")
    public CommonResult findRecode(Integer userId){
        List<RecodeVO> recodeVOS=orderService.findRecodeByUserId(userId);
        return CommonResultVO.success(recodeVOS);
    }

    /**
     * 根据用户id查询订单
     * @param userId
     * @return
     */
    @GetMapping("/goods/getOrder")
    public  CommonResult getOrder(Integer userId){
        List<OrderResultVO> orders=orderService.findOrder(userId);
        return CommonResultVO.success(orders);
    }
    @GetMapping("/goods/getOrderByModel")
    public CommonResult getOrderByModel(Integer userId,String sellModel){
        List<OrderResultVO> orders=orderService.findOrderByModel(userId,sellModel);
        return CommonResultVO
                .success(orders);
    }
}
