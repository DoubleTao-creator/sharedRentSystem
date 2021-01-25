package com.xyc.service;

import com.xyc.dto.SellerLoginDTO;
import com.xyc.dto.SellerRegisterDTO;
import com.xyc.pojo.Seller;
import org.springframework.stereotype.Service;


public interface SellerService {

    public int register(SellerRegisterDTO seller);

    public Seller login(SellerLoginDTO seller);


}
