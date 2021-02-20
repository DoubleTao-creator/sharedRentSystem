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
import org.springframework.web.multipart.MultipartFile;
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
        seller.setPic("");
        seller.setBalance(0);
        seller.setStatus("商家冻结中");
        seller.setLicense("");

        int i = sellerMapper.add(seller);

        if (i>0){

            int id = sellerMapper.getId(sellerRD.getName(),sellerRD.getEmail());
            sellerMapper.updateLicense(PhotoUtils.BASE_PREFIX+PhotoUtils.LICENSE_PREFIX
                            +id+PhotoUtils.SUFFIX,id);
            sellerMapper.updatePic(PhotoUtils.BASE_PREFIX+PhotoUtils.SELLER_PREFIX
                    +id+PhotoUtils.SUFFIX,id);
            try {
                FTPConstants fc = new FTPConstants();
                fc.setFilename(PhotoUtils.LICENSE_PREFIX+id+PhotoUtils.SUFFIX);
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
        }
        return i;
    }

    /**
     * 商家登录
     * @param sellerLD
     * @return
     */
    @Override
    public Seller login(SellerLoginDTO sellerLD) {

        Seller seller = sellerMapper.queryByName(sellerLD);
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
     * 同时状态变成‘商家冻结中’ 等待管理员审核
     * @param sellerMD
     * @return
     */
    @Override
    public int modifySeller(SellerModifyDTO sellerMD) {
        Seller seller = sellerMapper.queryById(sellerMD.getId());

        seller.setName(sellerMD.getName());
        seller.setPassword(MD5Utils.encode(sellerMD.getPassword()));
        seller.setTel(sellerMD.getTel());
        seller.setEmail(sellerMD.getEmail());
        //修改信息需要管理员重新审核
        seller.setStatus("商家冻结中");

        return sellerMapper.update(seller);
    }

    /**
     * 更换头像
     * @param pic
     * @param id
     * @return
     */
    @Override
    public boolean modifyPic(MultipartFile pic,Integer id) {
        boolean flag = false;
        try {
            FTPConstants fc = new FTPConstants();
            //删除原来的照片
            fc.setFilename(PhotoUtils.SELLER_PREFIX+id+PhotoUtils.SUFFIX);
            flag = PhotoUtils.deleteFile(fc);
            //上传新的照片
            File file = PhotoUtils.MultipartFileToFile(pic);
            fc.setInput(new FileInputStream(file));
            flag = PhotoUtils.uploadFile(fc);
            //删除本地缓存Temp
            PhotoUtils.deleteTempFile(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 更新营业执照
     * 同时状态变成‘商家冻结中’ 等待管理员审核
     * @param license
     * @param id
     * @return
     */
    @Override
    public boolean modifyLicense(MultipartFile license,Integer id) {
        boolean flag = false;
        try {
            FTPConstants fc = new FTPConstants();
            //删除原来的照片
            fc.setFilename(PhotoUtils.LICENSE_PREFIX+id+PhotoUtils.SUFFIX);
            flag = PhotoUtils.deleteFile(fc);
            //上传新的照片
            File file = PhotoUtils.MultipartFileToFile(license);
            fc.setInput(new FileInputStream(file));
            flag = PhotoUtils.uploadFile(fc);
            sellerMapper.updateLicense(PhotoUtils.BASE_PREFIX+PhotoUtils.LICENSE_PREFIX
                    +id+PhotoUtils.SUFFIX,id);
            //删除本地缓存Temp
            PhotoUtils.deleteTempFile(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
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
