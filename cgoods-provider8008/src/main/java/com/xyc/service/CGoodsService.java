package com.xyc.service;

import com.xyc.dto.CGoodsAddDTO;
import com.xyc.dto.CGoodsModifyDTO;
import com.xyc.pojo.CGoods;

import java.util.List;

public interface CGoodsService {

    public int add(CGoodsAddDTO cGoodsAD);

    public List<CGoods> getAll();

    public List<CGoods> searchBySellerId(Integer sellerId);

    public List<CGoods> fuzzySearch(String info);

    public List<CGoods> searchByTypeId(Integer typeId);

    public int updateInfo(CGoodsModifyDTO cGoodsAD);

}
