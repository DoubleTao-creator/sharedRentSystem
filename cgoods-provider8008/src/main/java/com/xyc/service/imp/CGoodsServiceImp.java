package com.xyc.service.imp;

import com.xyc.dto.CGoodsAddDTO;
import com.xyc.dto.CGoodsModifyDTO;
import com.xyc.dto.CGoodsShowDTO;
import com.xyc.mapper.CGoodsMapper;
import com.xyc.pojo.CGoods;
import com.xyc.service.CGoodsService;
import entity.FTPConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import utils.PhotoUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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

        for (String sellModel : cGoodsAD.getSellModels()) {
            System.out.println(sellModel);
        }
        Integer sellModelId = judgeSellModels(cGoodsAD.getSellModels());
        cGoods.setSellModel(sellModelId);

        System.out.println(cGoods);

//        上传商品照片到服务器
        try {
            FTPConstants fc = new FTPConstants();
            fc.setFilename(PhotoUtils.GOODS_PREFIX+cGoodsAD.getName()+PhotoUtils.SUFFIX);
            File file = PhotoUtils.MultipartFileToFile(cGoodsAD.getPic());
            fc.setInput(new FileInputStream(file));
            PhotoUtils.uploadFile(fc);
            System.out.println("商品类照片已上传");
            //删除本地临时文件 C:\UserData\AppData\Local\Temp目录下
            PhotoUtils.deleteTempFile(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cGoodsMapper.add(cGoods);
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
    public CGoodsShowDTO getById(Integer id) {
        return getCGoodsSD(cGoodsMapper.queryById(id));
    }

    @Override
    public List<CGoodsShowDTO> getAll() {
        List<CGoodsShowDTO> sdList = getCGoodsSDList(cGoodsMapper.queryAll());
        return sdList;
    }

    @Override
    public List<CGoodsShowDTO> searchBySellerId(Integer sellerId) {
        List<CGoodsShowDTO> sdList = getCGoodsSDList(cGoodsMapper.queryBySellerId(sellerId));
        return sdList;
    }

    @Override
    public List<CGoodsShowDTO> fuzzySearch(String info) {
        List<CGoodsShowDTO> sdList = getCGoodsSDList(cGoodsMapper.queryByInfo(info));
        return sdList;
    }

    @Override
    public List<CGoodsShowDTO> searchByTypeId(Integer typeId) {
        List<CGoodsShowDTO> sdList = getCGoodsSDList(cGoodsMapper.queryByTypeId(typeId));
        return sdList;
    }

    private List<CGoodsShowDTO> getCGoodsSDList(List<CGoods> list){
        List<CGoodsShowDTO> sdList = new ArrayList<>();
        for (CGoods cGoods : list) {
            sdList.add(getCGoodsSD(cGoods));
        }
        return sdList;
    }

    private CGoodsShowDTO getCGoodsSD(CGoods cGoods){
        CGoodsShowDTO cGoodsSD = new CGoodsShowDTO();

        cGoodsSD.setId(cGoods.getId());
        cGoodsSD.setSellerId(cGoods.getSellerId());
        cGoodsSD.setName(cGoods.getName());
        cGoodsSD.setType(getType(cGoods.getTypeId()));
        cGoodsSD.setRepertory(cGoods.getRepertory());
        cGoodsSD.setPic(cGoods.getPic());
        cGoodsSD.setInfo(cGoods.getInfo());
        cGoodsSD.setSellModels(getSellModels(cGoods.getSellModel()));
        cGoodsSD.setPrice(cGoods.getPrice());
        cGoodsSD.setRental(cGoods.getRental());
        cGoodsSD.setDeposit(cGoods.getDeposit());
        cGoodsSD.setStatus(cGoods.getStatus());

        return cGoodsSD;
    }

    private String[] getSellModels(int sellModel){
        String[] models;
        switch(sellModel){
            case 1 :
                models = new String[]{"以租代售"};
                break;
            case 2 :
                models = new String[]{"先租后买"};
                break;
            case 3 :
                models = new String[]{"先租后买","以租代售"};
                break;
            case 4 :
                models = new String[]{"共享租赁"};
                break;
            case 5 :
                models = new String[]{"共享租赁","以租代售"};
                break;
            case 6 :
                models = new String[]{"共享租赁","先租后买"};
                break;
            case 7 :
                models = new String[]{"共享租赁","先租后买","以租代售"};
                break;
            default: models = null;
        }
        return models;
    }

    private String getType(int typeId){
        String s = "";
        switch(typeId){
            case 1 :
                s = "电摩公社";
                break;
            case 2 :
                s = "电子产品";
                break;
            case 3 :
                s = "摄影航拍";
                break;
            case 4 :
                s = "家居生活";
                break;
            case 5 :
                s = "游戏酷玩";
                break;
            case 6 :
                s = "服饰潮鞋";
                break;
        }
        return s;
    }



}
