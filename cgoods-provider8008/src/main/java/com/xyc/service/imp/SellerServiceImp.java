package com.xyc.service.imp;

import com.xyc.mapper.SellerMapper;
import com.xyc.pojo.Seller;
import com.xyc.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImp implements SellerService {

    @Autowired
    private SellerMapper sellerMapper;

    @Override
    public Seller queryById(Integer id) {
        return sellerMapper.queryById(id);
    }
}
