package com.goods.service.impl;

import com.goods.dto.UserExperienceDTO;
import com.goods.entity.Cgoods;
import com.goods.entity.Goods;
import com.goods.entity.Installment;
import com.goods.entity.RentToBuy;
import com.goods.mapper.CGoodsMapper;
import com.goods.mapper.GoodsMapper;
import com.goods.mapper.SellerMapper;
import com.goods.mapper.UserMapper;
import com.goods.service.GoodsService;
import com.xtt.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xtt
 */
@Service
public class GoodsServiceImpl implements GoodsService{
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    CGoodsMapper cGoodsMapper;
    @Autowired
    SellerMapper sellerMapper;
    @Override
    public Integer ExperienceGoods(UserExperienceDTO userExperienceDTO) {
        String sellModel=userExperienceDTO.getSellModel();
        Integer userId=userExperienceDTO.getUserId();
        Integer CgoodsId=userExperienceDTO.getCgoodsId();
        Integer rentTime=userExperienceDTO.getRentTime();
        User user=userMapper.findUserById(userId);
        Cgoods cgoods=cGoodsMapper.getCgoodsById(CgoodsId);
        //处理以租代售订单
        if("以租代售".equals(sellModel)){
            //余额不足已支付本月月租
            if(user.getBalance()<cgoods.getRental()){
                return 0;
            }
            //向商品表和以租代售记录表加数据
            Installment installment=new Installment();
            goodsMapper.addInstallmentRecode(installment);
            Integer sell_id=installment.getId();
            installment.setId(sell_id);
            Goods goods=new Goods();
            goods.setSellId(sell_id);
            BeanUtils.copyProperties(userExperienceDTO, goods);
            goodsMapper.createInstallmentOrder(goods);
            Integer goodsId=goods.getId();
            installment.setGoodsId(goodsId);
            goodsMapper.updateInstallmentRecode(installment);
            //用户余额减少月租
            userMapper.changeUserbalance(userId, -cgoods.getRental());
            //商家增加对应余额
            sellerMapper.changeUserbalance(cgoods.getSellerId(), cgoods.getRental());
            //商品类库存减1
            cGoodsMapper.changeCGoodsRepertory(CgoodsId, -1);
            //生成订单记录->订单模块



            return 1;
        }else if("先租后买".equals(sellModel)){
            //处理先租后买订单
            //余额不足支付体验期订单
            if(user.getBalance()<cgoods.getRental()*rentTime){
                return 0;
            }
            RentToBuy rentToBuy=new RentToBuy();
            rentToBuy.setRentTime(rentTime);
            goodsMapper.addRentToBuyRecode(rentToBuy);
            Integer sellId=rentToBuy.getId();
            rentToBuy.setId(sellId);
            Goods goods=new Goods();
            goods.setSellId(sellId);
            BeanUtils.copyProperties(userExperienceDTO, goods);
            goodsMapper.createRentToBuyOrder(goods);
            Integer goodsId=goods.getId();
            rentToBuy.setGoodsId(goodsId);
            goodsMapper.updateRentToBuyRecode(rentToBuy);
            //用户余额减少月租
            userMapper.changeUserbalance(userId, -cgoods.getRental()*rentTime);
            //商家增加对应余额
            sellerMapper.changeUserbalance(cgoods.getSellerId(), cgoods.getRental()*rentTime);
            //商品类库存减1
            cGoodsMapper.changeCGoodsRepertory(CgoodsId, -1);
            //生成订单记录->订单模块


            return 1;
        }else if("共享租赁".equals(sellModel)){
            //处理共享租赁订单
            return 1;
        }
        return -1;
    }
}
