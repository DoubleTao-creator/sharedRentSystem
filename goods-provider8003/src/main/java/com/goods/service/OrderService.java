package com.goods.service;

import com.goods.vo.OrderResultVO;
import com.goods.vo.OwnedGoodsVO;
import com.goods.vo.RecodeVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xtt
 */
@Service
public interface OrderService {
    List<OwnedGoodsVO> findOwnedGoods(Integer userId);

    /**
     * 查询记录通过用户id
     * @param userId
     * @return
     */
    List<RecodeVO> findRecodeByUserId(Integer userId);

    /**
     * 查询订单通过用户id
     * @param userId
     * @return
     */
    List<OrderResultVO> findOrder(Integer userId);

    /**
     * 查询指定类型的订单
     * @param userId
     * @param sellModel
     * @return
     */
    List<OrderResultVO> findOrderByModel(Integer userId,String sellModel);
}
