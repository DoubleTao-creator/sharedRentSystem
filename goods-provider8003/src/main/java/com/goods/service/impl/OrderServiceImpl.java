package com.goods.service.impl;

import com.goods.entity.*;
import com.goods.mapper.*;
import com.goods.service.OrderService;
import com.goods.vo.*;
import entity.OrderRecode;
import entity.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
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
    @Autowired
    OrderMapper orderMapper;
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

    @Override
    public List<RecodeVO> findRecodeByUserId(Integer userId) {
        List<OrderRecode> recodes=orderMapper.findRecode(userId);
        List<RecodeVO> recodeVOS=new ArrayList<>();
        for(OrderRecode recode:recodes){
            Goods goods=goodsMapper.findGoodsById(recode.getGoodsId());
            Cgoods cgoods=cGoodsMapper.getCgoodsById(goods.getCgoodsId());
            com.xtt.entity.User user=userMapper.findUserById(userId);
            RecodeVO recodeVO=new RecodeVO();
            recodeVO.setCost(recode.getCost());
            recodeVO.setGoodsName(cgoods.getName());recodeVO.setGoodsPic(cgoods.getPic());recodeVO.setInfo(recode.getInfo());
            recodeVO.setModel(orderMapper.findModel(recode.getModelId()));recodeVO.setPaytime(recode.getPaytime());recodeVO.setUserName(user.getName());
            recodeVOS.add(recodeVO);
        }
        return recodeVOS;
    }

    @Override
    public List<OrderResultVO> findOrder(Integer userId) {
        List<Goods> orders=orderMapper.findGoodsByUserId(userId);
        List<OrderResultVO> resultVOS=new ArrayList<>();
        OrderResultVO orderResultVO=new OrderResultVO();
        for(Goods order:orders){
            Cgoods cgoods=cGoodsMapper.getCgoodsById(order.getCgoodsId());
            com.xtt.entity.User user=userMapper.findUserById(userId);
            Seller seller=sellerMapper.findSellerById(cgoods.getSellerId());
            BaseInformationVO baseInformationVO=new BaseInformationVO();
            baseInformationVO.setCgoodsId(cgoods.getId());baseInformationVO.setCgoodsName(cgoods.getName());baseInformationVO.setCgoodsPic(cgoods.getPic());baseInformationVO.setCgoodsPrice(cgoods.getPrice());baseInformationVO.setModel(orderMapper.findModel(order.getSellModel()));
            baseInformationVO.setSellerId(seller.getId());baseInformationVO.setSellerName(seller.getName());baseInformationVO.setStatus(order.getStatus());
            orderResultVO.setBase(baseInformationVO);
            //以租代售
            if(order.getSellId()==1){
                InstallmentGoodsVO installmentGoodsVO=new InstallmentGoodsVO();
                Installment installment=orderMapper.findInstalmentById(order.getSellId());
                installmentGoodsVO.setStartTime(installment.getStartTime());
                installmentGoodsVO.setDeadTime(installment.getDeadTime());
                //已付月数
                Integer diff=goodsMapper.selectDifferMonth(order.getId());
                //待付金额
                Double shouldPay=cgoods.getPrice()-diff*cgoods.getRental();
                installmentGoodsVO.setNotPay(shouldPay);
                orderResultVO.setExtra(installmentGoodsVO);
                resultVOS.add(orderResultVO);
            }else if(order.getSellModel()==2){
                //先租后买
                RentToBuyGoodsVO rentToBuyGoodsVO=new RentToBuyGoodsVO();
                RentToBuy rentToBuy=orderMapper.findRentToBuyById(order.getSellId());
                rentToBuyGoodsVO.setStartTime(rentToBuy.getStartTime());
                rentToBuyGoodsVO.setEndTime(goodsMapper.timeAfterAddMonth(rentToBuy.getStartTime(), rentToBuy.getRentTime()));
                rentToBuyGoodsVO.setTotalRentTime(rentToBuy.getRentTime());
                orderResultVO.setExtra(rentToBuyGoodsVO);
                resultVOS.add(orderResultVO);
            }else if(order.getSellModel()==3){
                //共享租赁
                ShareRent shareRent=orderMapper.findShareRentById(order.getSellId());
                ShareRentGoodsVO shareRentGoodsVO=new ShareRentGoodsVO();
                shareRentGoodsVO.setDeposit(shareRent.getDeposit());
                shareRentGoodsVO.setStartTime(shareRent.getStartTime());
                orderResultVO.setExtra(shareRent);
                resultVOS.add(orderResultVO);
            }
        }
        return resultVOS;
    }

}
