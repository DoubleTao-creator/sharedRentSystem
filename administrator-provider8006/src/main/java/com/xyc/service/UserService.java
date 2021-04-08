package com.xyc.service;

import com.xyc.pojo.User;

public interface UserService {

    public User getById(Integer id);

    public User getByName(String name);
}
