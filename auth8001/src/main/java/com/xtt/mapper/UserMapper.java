package com.xtt.mapper;
import com.xtt.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author xtt
 * @date
 */
@Mapper
@Component
public interface UserMapper {

    /**
     * 用户注册
     * @param user
     * @return
     */
    Integer userRegister(User user);

    /**
    * 根据用户名查询用户id
     * @param username
     * @return
     */
    Integer findIdByUsername(String username);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    String userLogin(String username,String password);
}
