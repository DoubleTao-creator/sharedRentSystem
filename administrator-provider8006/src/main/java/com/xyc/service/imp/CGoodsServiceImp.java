package com.xyc.service.imp;

import com.xyc.mapper.CGoodsMapper;
import com.xyc.pojo.CGoods;
import com.xyc.service.CGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CGoodsServiceImp implements CGoodsService {

    @Autowired
    private CGoodsMapper cGoodsMapper;

    @Override
    public List<CGoods> queryBySeller(Integer sellerId) {
        return cGoodsMapper.queryBySeller(sellerId);
    }

    @Override
    public int updateStatusToFrozen(Integer cgoodsId) {
        return cGoodsMapper.updateStatusToFrozen(cgoodsId);
    }
}
