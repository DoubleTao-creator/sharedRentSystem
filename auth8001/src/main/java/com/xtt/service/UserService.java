package com.xtt.service;

import com.xtt.dto.ModifyUserDTO;
import com.xtt.dto.UserDTO;
import com.xtt.entity.User;
import entity.CommonResult;
import entity.CommonResultVO;
import org.springframework.stereotype.Service;

/**
 * @author xtt
 * @date 2021/1/18
 */
@Service
public interface UserService {
    /**
     * 用户注册service
     * @param userDTO
     * @return
     */
    Integer userRegister(UserDTO userDTO);
    /**
     * 根据用户名查询用户ID
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

    /**
     * 更改用户信息
     * @param modifyUserDTO
     * @return
     */
    User modifyUser(ModifyUserDTO modifyUserDTO);

    /**
     * 根据ID查询用户
     * @param id
     * @return
     */
    User findUserById(Integer id);
}
