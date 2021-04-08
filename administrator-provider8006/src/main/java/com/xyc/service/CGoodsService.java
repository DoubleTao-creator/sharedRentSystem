package com.xyc.service;

import com.xyc.pojo.CGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CGoodsService {

    public List<CGoods> queryBySeller(Integer sellerId);

    public int updateStatusToFrozen(Integer cgoodsId);
}
