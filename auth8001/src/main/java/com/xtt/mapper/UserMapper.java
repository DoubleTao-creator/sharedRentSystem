package com.xtt.mapper;
import com.xtt.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
    User userLogin(@Param("username") String username,@Param("password") String password);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User findUserById(Integer id);

    /**
     * 更改用户基本信息
     * @param user
     */
    void modifyUser(User user);

    /**
     * 增加用户余额
     * @param userId
     * @param balance
     * @return
     */
    Integer addUserBalance(@Param("userId") Integer userId,@Param("balance") Double balance);

    /**
     * 更改用户密码
     * @param userId
     * @param password
     * @return
     */
    Integer modifyUserPassword(@Param("userId") Integer userId,@Param("password") String password);
}
