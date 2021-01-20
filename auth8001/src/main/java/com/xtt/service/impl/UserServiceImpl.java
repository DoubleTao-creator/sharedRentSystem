package com.xtt.service.impl;
/**
 * @author xtt
 * @date
 */

import com.xtt.dto.UserDTO;
import com.xtt.entity.User;
import com.xtt.mapper.UserMapper;
import com.xtt.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.MD5Utils;
import utils.PhotoUtils;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UserMapper userMapper;
    @Override
    public Integer userLogin(UserDTO userDTO) {
        //对密码进行加密
        String  encodedPassword= MD5Utils.encode(userDTO.getPassword());
        User user=new User();
        BeanUtils.copyProperties(userDTO, user);
        user.setPassword(encodedPassword);
        user.setBalance(0);
        user.setCredit(100);
        user.setPic(PhotoUtils.BASE_HEAD_PHOTO_URL);
        user.setRole("ROLE_USER");
        Integer num=userMapper.userLogin(user);
        return num;
    }
}
