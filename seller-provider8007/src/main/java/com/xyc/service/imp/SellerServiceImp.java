package com.xyc.service.imp;

import com.xyc.dto.SellerLoginDTO;
import com.xyc.dto.SellerRegisterDTO;
import com.xyc.mapper.SellerMapper;
import com.xyc.pojo.Seller;
import com.xyc.service.SellerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.MD5Utils;
import utils.PhotoUtils;

@Service
public class SellerServiceImp implements SellerService {

    @Autowired
    private SellerMapper sellerMapper;

    @Override
    public int register(SellerRegisterDTO sellerRD) {
        Seller seller = new Seller();
        BeanUtils.copyProperties(sellerRD,seller);

        seller.setPassword(MD5Utils.encode(sellerRD.getPassword()));
        seller.setPic(PhotoUtils.BASE_HEAD_PHOTO_URL);
        seller.setBalance(0);
        seller.setStatus("审核中");

        return sellerMapper.register(seller);
    }

    @Override
    public Seller login(SellerLoginDTO sellerLD) {
        Seller seller = sellerMapper.login(sellerLD);
        System.out.println(seller);

        if (seller!=null){
            MD5Utils md = new MD5Utils();
            if (md.matches(sellerLD.getPassword(),seller.getPassword())){
               return seller;
            }
        }

        return null;
    }
}
