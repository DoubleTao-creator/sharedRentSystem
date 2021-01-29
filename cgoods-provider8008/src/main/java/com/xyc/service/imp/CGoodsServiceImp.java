package com.xyc.service.imp;

import com.xyc.dto.CGoodsAddDTO;
import com.xyc.dto.CGoodsModifyDTO;
import com.xyc.mapper.CGoodsMapper;
import com.xyc.pojo.CGoods;
import com.xyc.service.CGoodsService;
import entity.FTPConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import utils.PhotoUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public class CGoodsServiceImp implements CGoodsService {

    @Autowired
    private CGoodsMapper cGoodsMapper;

    @Override
    public int updateInfo(CGoodsModifyDTO cGoodsMD) {
        CGoods cGoods = cGoodsMapper.queryById(cGoodsMD.getId());
        try {
            FTPConstants fc = new FTPConstants();
            //删除原来的照片
            fc.setFilename(PhotoUtils.SELLER_PREFIX+cGoods.getName()+PhotoUtils.SUFFIX);
            PhotoUtils.deleteFile(fc);
            //上传照片
            fc.setFilename(PhotoUtils.SELLER_PREFIX+cGoodsMD.getName()+PhotoUtils.SUFFIX);
            fc.setInput(new FileInputStream(PhotoUtils.transferToFile(cGoodsMD.getPic())));
            PhotoUtils.uploadFile(fc);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        cGoods.setName(cGoodsMD.getName());
        cGoods.setTypeId(cGoodsMD.getTypeId());
        cGoods.setRepertory(cGoodsMD.getRepertory());
        cGoods.setPic(PhotoUtils.GOODS_PREFIX+cGoodsMD.getName()+PhotoUtils.SUFFIX);
        cGoods.setInfo(cGoodsMD.getInfo());
        cGoods.setSellModel(judgeSellModels(cGoodsMD.getSellModels()));
        cGoods.setPrice(cGoodsMD.getPrice());
        cGoods.setRental(cGoodsMD.getRental());
        cGoods.setDeposit(cGoodsMD.getDeposit());
        cGoods.setStatus(cGoodsMD.getStatus());

        return cGoodsMapper.modify(cGoods);
    }

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

        Integer sellModelId = judgeSellModels(cGoodsAD.getSellModels());
        cGoods.setSellModel(sellModelId);

        cGoodsMapper.add(cGoods);

        return 0;
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

    @Override
    public List<CGoods> getAll() {
        List<CGoods> list = cGoodsMapper.queryAll();
        return list;
    }

    @Override
    public List<CGoods> searchBySellerId(Integer sellerId) {
        List<CGoods> list = cGoodsMapper.queryBySellerId(sellerId);
        return list;
    }

    @Override
    public List<CGoods> fuzzySearch(String info) {
        List<CGoods> list = cGoodsMapper.queryByInfo(info);
        return list;
    }

    @Override
    public List<CGoods> searchByTypeId(Integer typeId) {
        List<CGoods> list = cGoodsMapper.queryByTypeId(typeId);
        return list;
    }



}
