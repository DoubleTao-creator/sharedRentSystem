package com.xtt.controller;
/**
 * @Author xtt
 * @date 2021/1/20
 */

import com.xtt.dto.UserDTO;
import com.xtt.service.UserService;
import entity.CommonResult;
import entity.CommonResultVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import utils.ValidDataUtil;

@RestController
@Validated
@Api(value = "user-provider",description = "用户注册登录接口")
public class UserController {
    @Autowired
    public UserService userService;

    /**
     * 用户注册
     * @param userDTO
     * @param result
     * @return
     */
    @PostMapping("/user/login")
    public CommonResult userLogin(@Validated @RequestBody UserDTO userDTO, BindingResult result){
        //参数传入有误
        if(ValidDataUtil.validData(result)!=null){
            return CommonResultVO.error(ValidDataUtil.validData(result));
        }
        Integer num=userService.userLogin(userDTO);
        if(num>0){
            //注册成功
            System.out.println("用户: "+userDTO.getName()+"注册成功");
            return  CommonResultVO.success(null);
        }else{
            System.out.println("用户: "+userDTO.getName()+"注册失败");
            //注册失败
            return CommonResultVO.error("注册失败");
        }
    }
}
