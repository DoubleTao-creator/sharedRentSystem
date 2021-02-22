package com.goods.utils;

import com.goods.mapper.UserMapper;
import com.xtt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GoodsUtils {
    @Autowired
      UserMapper userMapper;
    public static  Integer  credit_add=5;
    public static Integer credit_sub=10;
    public  void addCredit(Integer userId,Integer credit){
        User user= userMapper.findUserById(userId);
        int creditToAdd=credit+user.getCredit();
        System.out.println(credit+"   "+creditToAdd);
        if(creditToAdd>100){
            creditToAdd=100;
        }
        if(creditToAdd<0){
            creditToAdd=0;
        }
        userMapper.addCredit(userId, creditToAdd);
    }
    public Double getRealDeposit(Integer credit,Double deposit){
        if(credit>=90){
            //90分以上免押金
            return 0.0;
        }else if(credit>=80&&credit<90){
            //80到90半款押金
            return deposit*0.5;
        }else {
            //80分以下全款押金
            return deposit;
        }
    }
}
