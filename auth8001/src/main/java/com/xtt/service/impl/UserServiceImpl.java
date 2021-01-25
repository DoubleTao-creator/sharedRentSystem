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
    public Integer userRegister(UserDTO userDTO) {
        //对密码进行加密
        String  encodedPassword= MD5Utils.encode(userDTO.getPassword());
        User user=new User();
        BeanUtils.copyProperties(userDTO, user);
        user.setPassword(encodedPassword);
        //设置余额
        user.setBalance(0);
        //设置信誉积分
        user.setCredit(100);
        //设置头像为初始默认头像
        user.setPic(PhotoUtils.BASE_HEAD_PHOTO_URL);
        //设置用户角色
        user.setRole("ROLE_USER");
        Integer num=userMapper.userRegister(user);
        return num;
    }
    @Override
    public Integer findIdByUsername(String username) {
        return userMapper.findIdByUsername(username);
    }
    @Override
    public String userLogin(String username, String password) {
        String name=userMapper.userLogin(username, MD5Utils.encode(password));
        return name;
    }
}
