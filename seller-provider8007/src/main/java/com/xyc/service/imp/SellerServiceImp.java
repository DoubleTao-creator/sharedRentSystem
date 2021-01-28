package com.xyc.service.imp;

import com.xyc.dto.SellerLoginDTO;
import com.xyc.dto.SellerModifyDTO;
import com.xyc.dto.SellerRegisterDTO;
import com.xyc.mapper.SellerMapper;
import com.xyc.pojo.Seller;
import com.xyc.service.SellerService;
import entity.FTPConstants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.MD5Utils;
import utils.PhotoUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class SellerServiceImp implements SellerService {

    @Autowired
    private SellerMapper sellerMapper;

    @Override
    public int register(SellerRegisterDTO sellerRD) {
        Seller seller = new Seller();

        seller.setName(sellerRD.getName());
        seller.setPassword(MD5Utils.encode(sellerRD.getPassword()));
        seller.setEmail(sellerRD.getEmail());
        seller.setTel(sellerRD.getTel());
        seller.setPic(PhotoUtils.BASE_HEAD_PHOTO_URL);
        seller.setBalance(0);
        seller.setStatus("审核中");
        seller.setLicense(PhotoUtils.LICENSE_PREFIX+sellerRD.getName()+PhotoUtils.SUFFIX);

        try {
            FTPConstants fc = new FTPConstants();
            fc.setFilename(PhotoUtils.LICENSE_PREFIX+sellerRD.getName()+PhotoUtils.SUFFIX);
            fc.setInput(new FileInputStream(PhotoUtils.transferToFile(sellerRD.getLicense())));
            PhotoUtils.uploadFile(fc);
            System.out.println("营业执照已上传");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sellerMapper.register(seller);
    }

    @Override
    public Seller login(SellerLoginDTO sellerLD) {
        Seller seller = sellerMapper.login(sellerLD);
        System.out.println(seller);

        if (seller!=null){
            System.out.println("用户存在");
            MD5Utils md = new MD5Utils();
            if (md.matches(sellerLD.getPassword(),seller.getPassword())){
               return seller;
            }
        }
        return null;
    }

    @Override
    public Seller queryById(int id){
        return sellerMapper.queryById(id);
    }

    @Override
    public int updateBalance(int income,int sellerId) {
        sellerMapper.updateBalance(income,sellerId);
        return 0;
    }

    @Override
    public Seller modifySeller(SellerModifyDTO sellerMD) {
        Seller seller = sellerMapper.queryById(sellerMD.getId());
        try {
            FTPConstants fc = new FTPConstants();

            fc.setFilename(PhotoUtils.SELLER_PREFIX+seller.getName()+PhotoUtils.SUFFIX);
            PhotoUtils.deleteFile(fc);

            fc.setFilename(PhotoUtils.SELLER_PREFIX+sellerMD.getName()+PhotoUtils.SUFFIX);
            fc.setInput(new FileInputStream(PhotoUtils.transferToFile(sellerMD.getPic())));
            PhotoUtils.uploadFile(fc);

            seller.setId(sellerMD.getId());
            seller.setName(sellerMD.getName());
            seller.setPassword(sellerMD.getPassword());
            seller.setTel(sellerMD.getTel());
            seller.setEmail(sellerMD.getEmail());
            seller.setPic(PhotoUtils.SELLER_PREFIX+sellerMD.getName()+PhotoUtils.SUFFIX);

            sellerMapper.update(seller);
            seller = sellerMapper.queryById(sellerMD.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return seller;
    }
}
