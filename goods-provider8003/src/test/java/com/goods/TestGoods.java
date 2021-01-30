package com.goods;

import com.goods.dto.UserExperienceDTO;
import com.goods.entity.Installment;
import com.goods.mapper.GoodsMapper;
import com.goods.mapper.UserMapper;
import com.goods.service.GoodsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestGoods {
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GoodsService goodsService;
    @Autowired
    UserMapper userMapper;
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
}
