package com.xyc.service;

import com.xyc.dto.SellerLoginDTO;
import com.xyc.dto.SellerModifyDTO;
import com.xyc.dto.SellerRegisterDTO;
import com.xyc.pojo.Seller;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;


public interface SellerService {

    public int register(SellerRegisterDTO seller);

    public Seller login(SellerLoginDTO seller);

    public int modifySeller(SellerModifyDTO sellerMD) throws FileNotFoundException;

    public Seller queryById(int id);

    public List<Seller> getFrozenAccount();

    public int sellerAuthenticate(int id);

    public int updateBalance(int income,int sellerId);


}
