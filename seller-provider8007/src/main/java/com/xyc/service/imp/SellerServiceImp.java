package com.xyc.service.imp;

import com.xyc.dto.SellerLoginDTO;
import com.xyc.dto.SellerModifyDTO;
import com.xyc.dto.SellerRegisterDTO;
import com.xyc.mapper.SellerMapper;
import com.xyc.pojo.Seller;
import com.xyc.service.SellerService;
import entity.FTPConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.MD5Utils;
import utils.PhotoUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public class SellerServiceImp implements SellerService {

    @Autowired
    private SellerMapper sellerMapper;

    /**
     * 商家注册
     * @param sellerRD
     * @return
     */
    @Override
    public int register(SellerRegisterDTO sellerRD) {
        Seller seller = new Seller();

        seller.setName(sellerRD.getName());
        seller.setPassword(MD5Utils.encode(sellerRD.getPassword()));
        seller.setEmail(sellerRD.getEmail());
        seller.setTel(sellerRD.getTel());
        seller.setPic(PhotoUtils.BASE_HEAD_PHOTO_URL);
        seller.setBalance(0);
        seller.setStatus("商家冻结中");
        seller.setLicense(PhotoUtils.BASE_PREFIX+PhotoUtils.LICENSE_PREFIX
                +sellerRD.getName()+PhotoUtils.SUFFIX);

        try {
            FTPConstants fc = new FTPConstants();
            fc.setFilename(PhotoUtils.LICENSE_PREFIX+sellerRD.getName()+PhotoUtils.SUFFIX);

            File file = PhotoUtils.MultipartFileToFile(sellerRD.getLicense());
            fc.setInput(new FileInputStream(file));
            PhotoUtils.uploadFile(fc);
            System.out.println("营业执照已上传");

            PhotoUtils.deleteTempFile(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sellerMapper.register(seller);
    }

    /**
     * 商家登录
     * @param sellerLD
     * @return
     */
    @Override
    public Seller login(SellerLoginDTO sellerLD) {
        Seller seller = sellerMapper.login(sellerLD);
        System.out.println(seller);

        if (seller!=null){
            System.out.println("用户存在");
            if (MD5Utils.matches(sellerLD.getPassword(),seller.getPassword())){
               return seller;
            }
        }
        return null;
    }

    /**
     * 商家修改个人信息  '余额' '状态' 不可修改
     * @param sellerMD
     * @return
     */
    @Override
    public int modifySeller(SellerModifyDTO sellerMD) {
        Seller seller = sellerMapper.queryById(sellerMD.getId());
        try {
            FTPConstants fc = new FTPConstants();
            //删除原来的照片
            fc.setFilename(PhotoUtils.SELLER_PREFIX+seller.getName()+PhotoUtils.SUFFIX);
            PhotoUtils.deleteFile(fc);
            //上传新的照片
            fc.setFilename(PhotoUtils.SELLER_PREFIX+sellerMD.getName()+PhotoUtils.SUFFIX);
            File file = PhotoUtils.MultipartFileToFile(sellerMD.getPic());
            fc.setInput(new FileInputStream(file));
            PhotoUtils.uploadFile(fc);

            PhotoUtils.deleteTempFile(file);

            seller.setId(sellerMD.getId());
            seller.setName(sellerMD.getName());
            seller.setPassword(sellerMD.getPassword());
            seller.setTel(sellerMD.getTel());
            seller.setEmail(sellerMD.getEmail());
            seller.setPic(PhotoUtils.BASE_PREFIX+PhotoUtils.SELLER_PREFIX
                    +sellerMD.getName()+PhotoUtils.SUFFIX);

            //一直想不清 修改信息能不能修改营业执照

            //修改信息需要管理员重新审核
            seller.setStatus("商家冻结中");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sellerMapper.update(seller);
    }

    /**
     * 查询商家信息 根据id
     * @param id
     * @return
     */
    @Override
    public Seller queryById(int id){
        return sellerMapper.queryById(id);
    }

    /**
     * 得到status（状态）为'冻结'状态的商家
     * @return
     */
    @Override
    public List<Seller> getFrozenAccount() {
        return sellerMapper.getFrozenAccount();
    }

    /**
     * 修改status为'正常营业' （商家认证）
     * @param id
     * @return
     */
    @Override
    public int sellerAuthenticate(int id) {
        return sellerMapper.sellerAuthenticate(id);
    }

    @Override
    public int updateBalance(int income,int sellerId) {
        sellerMapper.updateBalance(income,sellerId);
        return 0;
    }


}
