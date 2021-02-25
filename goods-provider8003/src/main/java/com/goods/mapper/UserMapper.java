package com.goods.mapper;


import entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author xtt
 */
@Mapper
@Component
public interface UserMapper {
    /**
     * 根据id查询用户信息
     * @param userId
     * @return
     */
    User findUserById(Integer userId);

    /**
     * 改变用户余额
     * @param userId
     * @param balanceToChange
     * @return
     */
    Integer changeUserbalance(@Param("userId") Integer userId, @Param("balanceToChange") Double balanceToChange);

    /**
     * 用户增加信誉积分
     * @param userId
     * @param credit
     * @return
     */
    Integer addCredit(@Param("userId") Integer userId,@Param("credit") Integer credit);
}
