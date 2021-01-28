package com.xyc.service.imp;

import com.xyc.dto.CGoodsAddDTO;
import com.xyc.mapper.CGoodsMapper;
import com.xyc.pojo.CGoods;
import com.xyc.service.CGoodsService;
import entity.FTPConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.PhotoUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class CGoodsServiceImp implements CGoodsService {

    @Autowired
    private CGoodsMapper cGoodsMapper;

    @Override
    public int add(CGoodsAddDTO cGoodsAD) {
        CGoods cGoods = new CGoods();
        cGoods.setSellerId(cGoodsAD.getSellerId());
        cGoods.setName(cGoodsAD.getName());
        cGoods.setTypeId(cGoodsAD.getTypeId());
        cGoods.setRepertory(cGoodsAD.getRepertory());
        cGoods.setPic(PhotoUtils.GOODS_PREFIX+cGoodsAD.getName()+PhotoUtils.SUFFIX);
        cGoods.setInfo(cGoodsAD.getInfo());
        cGoods.setPrice(cGoodsAD.getPrice());
        cGoods.setRental(cGoodsAD.getRental());
        cGoods.setDeposit(cGoodsAD.getDeposit());
        cGoods.setStatus("待审核");
        //上传商品照片到服务器
        uploadFile(cGoodsAD);


        return 0;
    }

    private void uploadFile(CGoodsAddDTO cGoodsAD){
        try {
            FTPConstants fc = new FTPConstants();
            fc.setFilename(PhotoUtils.GOODS_PREFIX+cGoodsAD.getName()+PhotoUtils.SUFFIX);
            fc.setInput(new FileInputStream(PhotoUtils.transferToFile(cGoodsAD.getPic())));
            PhotoUtils.uploadFile(fc);
            System.out.println("商品类照片已上传");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Integer judgeSellModels(String[] sellModels){
        Integer sellModel=0;
        //用二进制来表示三种租赁模式是否被选择 0：该商品不支持该模式
        //s[0]:共享租赁    s[1]:先租后买     s[2]:以租代售
        char[] s = new char[]{'0', '0', '0'};
        for (String model : sellModels) {
            if (model.equals("共享租赁")){
                s[0]='1';
            }
            else if (model.equals("先租后买")){
                s[1]='1';
            }
            else if (model.equals("以租代售")){
                s[2]='1';
            }
        }
        String ss = new String(s);
        sellModel = cGoodsMapper.getSellModel(ss);
        return sellModel;
    }
}
