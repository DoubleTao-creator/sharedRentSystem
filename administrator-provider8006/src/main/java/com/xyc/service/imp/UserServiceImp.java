package com.xyc.service.imp;

import com.xyc.mapper.UserMapper;
import com.xyc.pojo.User;
import com.xyc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getById(Integer id) {
        return userMapper.queryById(id);
    }

    @Override
    public User getByName(String name) {
        return userMapper.queryByName(name);
    }
}
