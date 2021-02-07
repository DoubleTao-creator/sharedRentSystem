package com.xyc.controller;

import entity.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/administrator")
public class ConsumerController {

    private static final String ORDER_PREFIX = "http://localhost:8002/orderRecord";
    private static final String CGOODS_PREFIX = "http://localhost:8008/cgoods";
    private static final String SELLER_PREFIX = "http://localhost:8007/seller";

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 管理员查看商品订单消费记录
     * @param goodsId
     * @return
     */
    @GetMapping("/orderRecord/{goodsId}")
    public CommonResult<List> getOrderRecordByGoodsId(@PathVariable("goodsId") Integer goodsId){
        return restTemplate.getForObject(ORDER_PREFIX+"/query/"+goodsId
                ,CommonResult.class);
    }

    /**
     * 管理员得到待审核的商品
     * @return
     */
    @GetMapping("/getCGoodsToApprove")
    public CommonResult<List> getUnapproveCGoods(){
        return restTemplate.getForObject(
                CGOODS_PREFIX+"/getCGoodsToApprove"
                ,CommonResult.class);
    }

    /**
     * 管理员通过商品审核 商品状态 '待审核'-->'正常'
     * @param goodsId
     * @return
     */
    @GetMapping("/approve/{goodsId}")
    public CommonResult cGoodsApprove(@PathVariable("goodsId") Integer goodsId){
        return restTemplate.getForObject(
                CGOODS_PREFIX+"/approve/"+goodsId
                ,CommonResult.class);
    }

    /**
     * 管理员得到需要认证的商户
     * @return
     */
    @GetMapping("/getFrozenSeller")
    public CommonResult<List> getFrozenSeller(){
        return restTemplate.getForObject(
                SELLER_PREFIX+"/frozenAccount"
                ,CommonResult.class);
    }

    /**
     * 管理员认证商家
     * @param sellerId
     * @return
     */
    @GetMapping("/authenticate/{sellerId}")
    public CommonResult authenticate(@PathVariable("sellerId") Integer sellerId){
        return restTemplate.getForObject(
                SELLER_PREFIX+"/authenticate/"+sellerId
                ,CommonResult.class);
    }




}
