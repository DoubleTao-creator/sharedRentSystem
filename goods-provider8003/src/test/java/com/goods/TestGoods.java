package com.goods;

import com.goods.dto.UserExperienceDTO;
import com.goods.entity.Installment;
import com.goods.mapper.GoodsMapper;
import com.goods.mapper.UserMapper;
import com.goods.service.GoodsService;
import com.goods.service.OrderService;
import com.goods.utils.GoodsUtils;
import com.goods.vo.OrderResultVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class TestGoods {
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GoodsService goodsService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    OrderService orderService;
    @Test
    public  void test1(){
        Installment installment=new Installment();
        System.out.println("修改行数"+goodsMapper.addInstallmentRecode(installment));
        System.out.println("插入后id"+installment.getId());
    }
    @Test
    public void test2(){
        UserExperienceDTO userExperienceDTO=new UserExperienceDTO();
        userExperienceDTO.setCgoodsId(1);
        userExperienceDTO.setSellModel("以租代售");
        userExperienceDTO.setUserId(1);
        goodsService.ExperienceGoods(userExperienceDTO);
    }
    @Test
    public  void test3(){
        userMapper.changeUserbalance(2, -100.0);
    }
    @Test
    public void test4(){
        UserExperienceDTO userExperienceDTO=new UserExperienceDTO();
        userExperienceDTO.setCgoodsId(1);
        userExperienceDTO.setSellModel("先租后买");
        userExperienceDTO.setUserId(2);
        userExperienceDTO.setRentTime(2);
        goodsService.ExperienceGoods(userExperienceDTO);
    }
    @Test
    public void test5(){
        //goodsMapper.rerentGoods(1);
        Installment installment=goodsMapper.findInstallmentByGoodsId(1);
        Timestamp timestamp=installment.getStartTime();
        Timestamp timestamp1=installment.getDeadTime();
        System.out.println(timestamp);
        System.out.println(timestamp1);
        System.out.println(timestamp1.getNanos());
    }
    @Test
    public void test6(){
        Integer differMonth=goodsMapper.selectDifferMonth(1);
        System.out.println(differMonth);
    }
    @Test
    public void testPurchase(){
        List<OrderResultVO> list=orderService.findOrder(2);
        for(OrderResultVO orderResultVO:list){
            System.out.println(orderResultVO);
        }
    }
    @Test
    public void testTime(){
        Installment installment=goodsMapper.findInstallmentByGoodsId(7);
       // System.out.println(installment.getDeadTime());
        Timestamp timestamp1=installment.getDeadTime();
        Timestamp timestamp2=new Timestamp(System.currentTimeMillis());
        //System.out.println(new Timestamp(new Date().getTime()));
        System.out.println(timestamp2.compareTo(timestamp1));
    }
    @Test
    public void  testCredit(){
        userMapper.findUserById(1);
        //GoodsUtils.addCredit(1, GoodsUtils.credit_add);
        orderService.findOwnedGoods(1);
    }
}
