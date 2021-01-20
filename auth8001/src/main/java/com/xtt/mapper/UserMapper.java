package com.xtt.mapper;
import com.xtt.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xtt
 * @date
 */
@Mapper
public interface UserMapper {

    /**
     * 用户注册
     * @param user
     * @return
     */
    Integer userLogin(User user);
}
