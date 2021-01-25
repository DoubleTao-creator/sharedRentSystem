package com.xtt.mapper;
import com.xtt.dto.ModifyUserDTO;
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
    User userLogin(String username,String password);
    //User modifyUser(ModifyUserDTO modifyUserDTO);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User findUserById(Integer id);

    /**
     * 更改用户信息
     * @param user
     */
    void modifyUser(User user);
}
