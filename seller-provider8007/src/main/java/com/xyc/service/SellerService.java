package com.xyc.service;

import com.xyc.dto.SellerLoginDTO;
import com.xyc.dto.SellerModifyDTO;
import com.xyc.dto.SellerRegisterDTO;
import com.xyc.pojo.Seller;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;


public interface SellerService {

    public int register(SellerRegisterDTO seller);

    public Seller login(SellerLoginDTO seller);

    public Seller modifySeller(SellerModifyDTO sellerMD) throws FileNotFoundException;

    public Seller queryById(int id);

    public int updateBalance(int income,int sellerId);

}
