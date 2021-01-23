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
public class OrderRecordController {

    @Autowired
    private OrderRecordMapper orderRecordMapper;


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

    @GetMapping("/query/{id}")
    public CommonResult<List> queryByGoodsId(@PathVariable("id") Integer id){
        List<OrderRecord> orderRecords = orderRecordMapper.queryByGoodsId(id);
        return CommonResultVO.success(orderRecords);
    }

}
