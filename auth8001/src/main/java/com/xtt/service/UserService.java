package com.xtt.service;

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
    Integer userLogin(UserDTO userDTO);
}
