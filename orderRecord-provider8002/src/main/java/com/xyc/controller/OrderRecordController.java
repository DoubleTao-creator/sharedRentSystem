package com.xyc.controller;

import com.xyc.dto.OrderRecordDTO;
import com.xyc.mapper.OrderRecordMapper;
import com.xyc.pojo.OrderRecord;
import entity.CommonResult;
import entity.CommonResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orderRecord")
public class OrderRecordController {

    @Autowired
    private OrderRecordMapper orderRecordMapper;

    /**
     * 根据查询用户商品订单记录
     * @param goodsId
     * @return
     */
    @GetMapping("/query/{goodsId}")
    public CommonResult<List> queryByGoodsId(@PathVariable("goodsId") Integer goodsId){
        List<OrderRecord> orderRecords = orderRecordMapper.queryByGoodsId(goodsId);
        return CommonResultVO.success(orderRecords);
    }


    /**
     * 增加某个商品的某条订单消费记录
     * @return
     */
    @RequestMapping("/add")
    public CommonResult add(OrderRecordDTO recordDTO){

        Integer i = orderRecordMapper.add(recordDTO);
        if (i>0){
            return CommonResultVO.success(null);
        }else {
            return CommonResultVO.error("支付失败，订单取消，记录添加失败");
        }

    }

}
