package com.goods.service.impl;

import com.goods.dto.UserExperienceDTO;
import com.goods.entity.*;
import com.goods.mapper.*;
import com.goods.service.GoodsService;
import com.goods.utils.GoodsUtils;
import com.xtt.entity.User;
import entity.OrderRecode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;

/**
 * @author xtt
 */
@Service
public class GoodsServiceImpl implements
        GoodsService{
    private Integer credit_add=5;
    private Integer credit_sub=10;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    CGoodsMapper cGoodsMapper;
    @Autowired
    SellerMapper sellerMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    GoodsUtils goodsUtils;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer ExperienceGoods(UserExperienceDTO userExperienceDTO) {
        Integer userId=userExperienceDTO.getUserId();
        User user=userMapper.findUserById(userId);
        if(user.getCredit()<60){
            //信誉分低于60无法下单
            return 2;
        }
        String sellModel=userExperienceDTO.getSellModel();
        Integer CgoodsId=userExperienceDTO.getCgoodsId();
        Integer rentTime=userExperienceDTO.getRentTime();
        Cgoods cgoods=cGoodsMapper.getCgoodsById(CgoodsId);
        //处理以租代售订单
        if("以租代售".equals(sellModel)){
            //余额不足已支付本月月租
            if(user.getBalance()<cgoods.getRental()){
                System.out.println("用户"+user.getName()+"余额不足,返回");
                return 0;
            }
            //向商品表和以租代售记录表加数据
            Installment installment=new Installment();
            goodsMapper.addInstallmentRecode(installment);
            Integer sellId=installment.getId();
            installment.setId(sellId);
            Goods goods=new Goods();
            goods.setSellId(sellId);
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
            //生成付款记录
            System.out.println("用户id: "+user.getId()+"商家id: "+sellId+"成功交易"+cgoods.getRental()+"元");
            System.out.println("用户id: "+user.getId()+"成功以租代售订单,商品类id: "+CgoodsId);
            OrderRecode orderRecode=new OrderRecode();
            orderRecode.setUserId(userId);orderRecode.setGoodsId(goodsId);orderRecode.setCost(cgoods.getRental());
            orderRecode.setModelId(1);
            orderRecode.setInfo("以租代售商品下单");
            orderMapper.addOrderRecode(orderRecode);
            return 1;
        }else if("先租后买".equals(sellModel)){
            //处理先租后买订单
            //余额不足支付体验期订单
            if(user.getBalance()<cgoods.getRental()*rentTime){
                System.out.println("用户"+user.getName()+"余额不足，返回");
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
            System.out.println("用户id: "+user.getId()+"商家id: "+sellId+"成功交易"+cgoods.getRental()*rentTime+"元");
            System.out.println("用户id: "+user.getId()+"成功先租后买订单,商品类id: "+CgoodsId+"体验期"+rentTime+"个月");
            OrderRecode orderRecode=new OrderRecode();
            orderRecode.setUserId(userId);orderRecode.setGoodsId(goodsId);orderRecode.setCost(cgoods.getRental()*rentTime);
            orderRecode.setModelId(2);orderRecode.setInfo("先租后买下单");
            orderMapper.addOrderRecode(orderRecode);
            return 1;
        }else if("共享租赁".equals(sellModel)){
            //处理共享租赁订单
            ShareRent shareRent=new ShareRent();
            //当前押金(关联信誉积分）
            Double currentDeposit=goodsUtils.getRealDeposit(user.getCredit(), cgoods.getDeposit());
            shareRent.setDeposit(currentDeposit);

            goodsMapper.addShareRentRecode(shareRent);
            Integer sellId=shareRent.getId();
            Goods goods=new Goods();
            BeanUtils.copyProperties(userExperienceDTO, goods);
            goods.setSellId(sellId);
            goodsMapper.createShareRentOrder(goods);
            Integer goodsId=goods.getId();
            shareRent.setGoodsId(goodsId);
            shareRent.setId(sellId);
            goodsMapper.updateShareRentRecode(shareRent);

            //用户余额减少押金
            userMapper.changeUserbalance(userId, -currentDeposit);
            //商家增加押金
            sellerMapper.changeUserbalance(cgoods.getSellerId(), currentDeposit);
            //商品类库存减1
            cGoodsMapper.changeCGoodsRepertory(CgoodsId, -1);
            //生成订单记录->订单模块
            System.out.println("用户id: "+user.getId()+"成功共享租赁订单,商品类id: "+CgoodsId);
            //订单记录-->订单模块
            OrderRecode orderRecode=new OrderRecode();
            orderRecode.setUserId(userId);orderRecode.setGoodsId(goodsId);orderRecode.setCost(currentDeposit);
            orderRecode.setModelId(3);orderRecode.setInfo("共享租赁下单");
            orderMapper.addOrderRecode(orderRecode);

            return 1;
        }
        return -1;
    }

    @Override
    public Integer purchaseGoods( Integer cgoodsId,Integer userId) {
        System.out.println("cgoodsId "+cgoodsId+" "+"userId "+userId);
        Cgoods cgoods=cGoodsMapper.getCgoodsById(cgoodsId);
        System.out.println(cgoods);
        Double price=cgoods.getPrice();
        Double balance=userMapper.findUserById(userId).getBalance();
        if(balance<price){
            //余额不足
            return  0;
        }
        Integer result=goodsMapper.purchaseGoods(cgoodsId, userId);
        //用户余额减少
        userMapper.changeUserbalance(userId, -price);
        //商家余额增加
        sellerMapper.changeUserbalance(cgoods.getSellerId(), price);
        //商品库存减少
        cGoodsMapper.changeCGoodsRepertory(cgoodsId, -1);
        if(result>=0){
            //信誉积分增加
            goodsUtils.addCredit(userId, GoodsUtils.credit_add);
            return 1;
        }else {
            return -1;
        }
    }

    @Override
    public Integer rerentGoods(Integer goodsId, Integer userId) {
        Double balance=userMapper.findUserById(userId).getBalance();
        Integer cGoodsId=goodsMapper.findGoodsById(goodsId).getCgoodsId();
        Cgoods cgoods=cGoodsMapper.getCgoodsById(cGoodsId);
        Double rent=cgoods.getRental();
        //用户已支付的月数
        Integer differMonth=goodsMapper.selectDifferMonth(goodsId);
        Double rentMonth1=Math.floor(cgoods.getPrice()/cgoods.getRental());
        //商品需要支付的月数
        Integer rentMonth=rentMonth1.intValue();
        if(differMonth>=rentMonth){
            orderMapper.changeGoodsStatus(goodsId, "已购买");
            //商品不用再续租
            return 2;
        }
        Installment installment=goodsMapper.findInstallmentByGoodsId(goodsId);
        if(balance<rent){
            //余额不足
            return 0;
        }
        //续租，订单下次交租时间加30天
        Integer result=goodsMapper.rerentGoods(goodsId);
        //用户余额减少
        userMapper.changeUserbalance(userId, -rent);
        //商家余额增加
        sellerMapper.changeUserbalance(cgoods.getSellerId(), rent);
        //生成付款记录
        OrderRecode orderRecode=new OrderRecode();
        orderRecode.setUserId(userId);orderRecode.setGoodsId(goodsId);orderRecode.setCost(rent);
        orderRecode.setModelId(1);
        orderRecode.setInfo("续租以租代售");
        orderMapper.addOrderRecode(orderRecode);
        if(result>0){
            //续租成功
            //增加信誉积分
            goodsUtils.addCredit(userId, GoodsUtils.credit_add);
            return 1;
        }else {
            //续租失败
            return -1;
        }
    }

    @Override
    public Integer purchaseRentToBuy(Integer goodsId, Integer userId) {
        Goods goods=goodsMapper.findGoodsById(goodsId);
        Cgoods cgoods=cGoodsMapper.getCgoodsById(goods.getCgoodsId());
        User user=userMapper.findUserById(userId);
        //商品总费用
        Double price=cgoods.getPrice();
        //用户体验期支付费用
        System.out.println(cGoodsMapper);
        System.out.println(goodsId+" "+userId);
        System.out.println(goodsMapper.findRentToBuyById(goodsId));
        System.out.println(cgoods.getRental());
        Double havePay=goodsMapper.findRentToBuyById(goodsId).getRentTime()*cgoods.getRental();
        System.out.println("havePay"+havePay);
        //用户余额
        Double balance=user.getBalance();
        //用户应该支付费用
        Double shouldPay=price-havePay;
        if(balance<shouldPay){
            //用户余额不足
            return 0;
        }
        //用户减少余额
        userMapper.changeUserbalance(userId, -shouldPay);
        //商家增加余额
        sellerMapper.changeUserbalance(cgoods.getSellerId(), shouldPay);
        //改订单为已购买
        goodsMapper.purchaseRentToBuy(goodsId);
        OrderRecode orderRecode=new OrderRecode();
        orderRecode.setUserId(userId);orderRecode.setGoodsId(goodsId);orderRecode.setCost(shouldPay);
        orderRecode.setModelId(2);orderRecode.setInfo("购买先租后买");
        orderMapper.addOrderRecode(orderRecode);
        //信誉分增加
        goodsUtils.addCredit(userId, GoodsUtils.credit_add);
        return 1;
    }
    @Override
    public Integer refundRent(Integer goodsId, Integer userId) {
        Goods goods=goodsMapper.findGoodsById(goodsId);
        //改商品为空闲
        Integer result=goodsMapper.refundRent(goodsId, userId);
        if(result>0){
            //商品库存增加
            cGoodsMapper.changeCGoodsRepertory(goods.getCgoodsId(), 1);
            OrderRecode orderRecode=new OrderRecode();
            orderRecode.setUserId(userId);orderRecode.setGoodsId(goodsId);
            orderRecode.setModelId(2);orderRecode.setInfo("退租先租后买");
            orderMapper.addOrderRecode(orderRecode);
            //信誉分增加
            goodsUtils.addCredit(userId, GoodsUtils.credit_add);
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public Integer settleShareRent(Integer goodsId, Integer userId) {
        User user=userMapper.findUserById(userId);
        Goods goods=goodsMapper.findGoodsById(goodsId);
        Cgoods cgoods=cGoodsMapper.getCgoodsById(goods.getCgoodsId());
        Integer differDay=goodsMapper.differDayShareRent(goodsId);
        if(differDay==0){
            //不满一天，按一天算
            differDay=1;
        }
        //用户余额
        Double balance=user.getBalance();
        //用户应该付的钱（按天计费）
        Double shouldPay=differDay*cgoods.getRental()/30;
        //押金
        Double deposit=goodsMapper.findShareRentByGoodsId(goodsId).getDeposit();
        if(balance<shouldPay){
            //余额不足
            return 0;
        }
        //用户减少余额
        userMapper.changeUserbalance(userId, -shouldPay);
        //商家增加余额
        sellerMapper.changeUserbalance(cgoods.getSellerId(), shouldPay);
        //用户增加押金
        userMapper.changeUserbalance(userId, deposit);
        //记录
        OrderRecode orderRecode1=new OrderRecode();
        orderRecode1.setUserId(userId);orderRecode1.setGoodsId(goodsId);orderRecode1.setCost(deposit);
        orderRecode1.setModelId(3);orderRecode1.setInfo("押金归还"+deposit+"元");
        orderMapper.addOrderRecode(orderRecode1);
        //商家减少押金
        sellerMapper.changeUserbalance(cgoods.getSellerId(), -deposit);
        //结算成功，将商品改为空闲状态
        goodsMapper.changeShareRentStatus(goodsId, "空闲");
        //商品库存加1
        cGoodsMapper.changeCGoodsRepertory(cgoods.getId(), 1);
        //生成付款记录
        OrderRecode orderRecode=new OrderRecode();
        orderRecode.setUserId(userId);orderRecode.setGoodsId(goodsId);orderRecode.setCost(shouldPay);
        orderRecode.setModelId(3);orderRecode.setInfo("结算共享租赁"+",使用天数"+differDay);
        orderMapper.addOrderRecode(orderRecode);
        //增加信誉积分
        goodsUtils.addCredit(userId, GoodsUtils.credit_add);
        return 1;
    }

}
