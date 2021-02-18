package com.goods.utils;

import com.goods.mapper.UserMapper;
import com.xtt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GoodsUtils {
    @Autowired
    static UserMapper userMapper;
    public static void addCredit(Integer userId,Integer credit){
        User user= userMapper.findUserById(userId);
        credit=user.getCredit()+credit;
        if(credit>100){
            credit=100;
        }
        if(credit<0){
            credit=0;
        }
        userMapper.addCredit(userId, credit);
    }
}
