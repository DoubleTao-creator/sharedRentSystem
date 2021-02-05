package com.goods.service;

import com.goods.vo.OwnedGoodsVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xtt
 */
@Service
public interface OrderService {
    List<OwnedGoodsVO> findOwnedGoods(Integer userId);
}
