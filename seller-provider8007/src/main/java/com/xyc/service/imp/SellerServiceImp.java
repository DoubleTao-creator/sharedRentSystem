package com.xyc.service.imp;

import com.xyc.dto.SellerLoginDTO;
import com.xyc.dto.SellerModifyDTO;
import com.xyc.dto.SellerRegisterDTO;
import com.xyc.mapper.SellerMapper;
import com.xyc.pojo.Seller;
import com.xyc.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import utils.MD5Utils;
import utils.PhotoUtils;

import java.io.*;
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
    @Transactional(rollbackFor = Exception.class)
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
        seller.setLicense("");

        int i = sellerMapper.add(seller);

        if (i>0){

            int id = sellerMapper.getId(sellerRD.getName(),sellerRD.getEmail());
            sellerMapper.updateLicense(PhotoUtils.BASE_PREFIX+PhotoUtils.LICENSE_PREFIX
                            +id+PhotoUtils.SUFFIX,id);
            File fileParent=new File("/photo");
            if(fileParent.exists()){
                fileParent.mkdir();
            }
            File newFile=new File("/photo/",PhotoUtils.LICENSE_PREFIX+id+PhotoUtils.SUFFIX);
            if(!newFile.exists()){
                boolean flag= false;
                try {
                    flag = newFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(flag);
            }else {
                System.out.println("文件已存在"+newFile.getAbsolutePath());
            }
            try {
                sellerRD.getLicense().transferTo(newFile);
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
    @Transactional
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
    public boolean modifyPic(String oldName,MultipartFile pic,Integer id) {
        boolean flag = true;
        //如果照片是默认头像的话，更新数据库 商家头像字段
        if (oldName.equals(PhotoUtils.BASE_HEAD_PHOTO_URL)){
            sellerMapper.updatePic(PhotoUtils.BASE_PREFIX+PhotoUtils.SELLER_PREFIX
                    +id+PhotoUtils.SUFFIX,id);
        }
        try {
            PhotoUtils.uploadPic(pic, PhotoUtils.SELLER_PREFIX + id + PhotoUtils.SUFFIX);
        }catch (Exception e){
            return false;
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
        boolean flag = true;
        try {
            PhotoUtils.uploadPic(license, PhotoUtils.LICENSE_PREFIX+id+PhotoUtils.SUFFIX);
        }catch (Exception e){
            return false;
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
    @Transactional
    public int sellerAuthenticate(int id) {
        return sellerMapper.sellerAuthenticate(id);
    }

    @Transactional
    @Override
    public int updateBalance(int income,int sellerId) {
        sellerMapper.updateBalance(income,sellerId);
        return 0;
    }
}
