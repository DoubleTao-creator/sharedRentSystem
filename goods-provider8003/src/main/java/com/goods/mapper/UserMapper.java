package com.goods.mapper;

import com.xtt.entity.User;
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
}
