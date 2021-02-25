package com.xtt.service.impl;
/**
 * @author xtt
 * @date
 */
import com.xtt.dto.ModifyUserDTO;
import com.xtt.dto.PassWordDTO;
import com.xtt.dto.UserDTO;
import com.xtt.entity.User;
import com.xtt.mapper.UserMapper;
import com.xtt.service.UserService;
import entity.FTPConstants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.awt.geom.AreaOp;
import utils.MD5Utils;
import utils.PhotoUtils;

import java.io.FileInputStream;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UserMapper userMapper;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer userRegister(UserDTO userDTO) {
        //对密码进行加密
        String  encodedPassword= MD5Utils.encode(userDTO.getPassword());
        User user=new User();
        BeanUtils.copyProperties(userDTO, user);
        user.setPassword(encodedPassword);
        //设置余额
        user.setBalance(0.0);
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
    public User userLogin(String username, String password) {
        User user=userMapper.userLogin(username, MD5Utils.encode(password));
        return user;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public User modifyUser(ModifyUserDTO modifyUserDTO) {
        User modifiedUser;
        try{
            FTPConstants ftpConstants=new FTPConstants();
            ftpConstants.setFilename(PhotoUtils.USER_PREFIX+modifyUserDTO.getId()+PhotoUtils.SUFFIX);
            ftpConstants.setInput(new FileInputStream(PhotoUtils.MultipartFileToFile(modifyUserDTO.getFile())));
            //删除原来的头像
            PhotoUtils.deleteFile(ftpConstants);
            //上传新的头像
            PhotoUtils.uploadFile(ftpConstants);
            User user=new User();
            user.setId(modifyUserDTO.getId());
            user.setName(modifyUserDTO.getName());
            user.setEmail(modifyUserDTO.getEmail());
            user.setTel(modifyUserDTO.getTel());
            user.setPic(PhotoUtils.BASE_PREFIX+PhotoUtils.USER_PREFIX+modifyUserDTO
            .getId()+PhotoUtils.SUFFIX);
            //更改用户信息
            userMapper.modifyUser(user);
            //查询更改后的用户信息
            modifiedUser=userMapper.findUserById(modifyUserDTO.getId());
        }catch (Exception e){
            return null;
        }
        //返回更改后的用户信息
        return modifiedUser;
    }

    @Override
    public User findUserById(Integer id) {
        return userMapper.findUserById(id);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer modifyUserPassword(PassWordDTO passWordDTO) {
        User user=userMapper.findUserById(passWordDTO.getUserId());
        if(!MD5Utils.matches(passWordDTO.getOldPassword(), user.getPassword())){
            //原密码错误,返回
            return 0;
        }
        //更改密码
        userMapper.modifyUserPassword(passWordDTO.getUserId(), MD5Utils.encode(passWordDTO.getNewPassword()));
        return 1;
    }

    @Override
    public Integer reCharge(Integer userId,Double money) {
        Integer result=userMapper.reCharge(userId, money);
        if(result>0){
            return 1;
        }else {
            return 0;
        }
    }
}
