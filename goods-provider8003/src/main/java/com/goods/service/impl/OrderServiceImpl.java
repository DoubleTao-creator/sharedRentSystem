package com.goods.service.impl;

import com.goods.entity.Cgoods;
import com.goods.entity.Goods;
import com.goods.mapper.CGoodsMapper;
import com.goods.mapper.GoodsMapper;
import com.goods.mapper.SellerMapper;
import com.goods.mapper.UserMapper;
import com.goods.service.OrderService;
import com.goods.vo.OwnedGoodsVO;
import entity.Seller;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xtt
 */
public class OrderServiceImpl implements OrderService{
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    CGoodsMapper cGoodsMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    SellerMapper sellerMapper;
    @Override
    public List<OwnedGoodsVO> findOwnedGoods(Integer userId) {
        List<Goods> goods=goodsMapper.findOwnedGoods(userId);
        List<OwnedGoodsVO> list=new ArrayList<>();
        for(Goods goods1:goods){
            Cgoods cgoods=cGoodsMapper.getCgoodsById(goods1.getCgoodsId());
            Seller seller=sellerMapper.findSellerById(cgoods.getSellerId());
            OwnedGoodsVO ownedGoodsVO=new OwnedGoodsVO();
            ownedGoodsVO.setCgoodsId(cgoods.getId());
            ownedGoodsVO.setId(goods1.getId());ownedGoodsVO.setCgoodsInfo(cgoods.getInfo());ownedGoodsVO.setCgoodsName(cgoods.getName());ownedGoodsVO.setCgoodsPic(cgoods.getPic());ownedGoodsVO.setPrice(cgoods.getPrice());
            ownedGoodsVO.setSellerId(seller.getId());ownedGoodsVO.setSellerName(seller.getName());ownedGoodsVO.setStatus(seller.getStatus());
            list.add(ownedGoodsVO);
        }
        return list;
    }
}
