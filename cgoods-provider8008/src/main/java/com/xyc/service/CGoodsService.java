package com.xyc.service;

import com.xyc.dto.CGoodsAddDTO;
import com.xyc.dto.CGoodsModifyDTO;
import com.xyc.dto.CGoodsShowDTO;
import com.xyc.dto.GoodsShowDTO;
import com.xyc.pojo.CGoods;

import java.util.List;

public interface CGoodsService {

    public CGoodsShowDTO getById(Integer id);

    public int add(CGoodsAddDTO cGoodsAD);

    public List<CGoodsShowDTO> getAll();

    public List<CGoodsShowDTO> searchBySellerId(Integer sellerId);

    public List<CGoodsShowDTO> fuzzySearch(String info);

    public List<CGoodsShowDTO> searchByTypeId(Integer typeId);

    public int updateInfo(CGoodsModifyDTO cGoodsAD);

    public List<GoodsShowDTO> getEachGoodsByCGoodId(Integer cGoodsId);

    public List<CGoodsShowDTO> getByStatus();

    public int changeStatus(Integer id);
}
