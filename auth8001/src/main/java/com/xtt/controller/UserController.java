package com.xtt.controller;
/**
 * @Author xtt
 * @date 2021/1/20
 */
import com.xtt.dto.UserDTO;
import com.xtt.service.UserService;
import com.xtt.util.MailUtils;
import entity.CommonResult;
import entity.CommonResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utils.ValidDataUtil;

@RestController
@Validated
public class UserController {
    @Autowired
    public UserService userService;
    @Autowired
    public MailUtils mailUtils;
    /**
     * 用户注册
     * @param userDTO
     * @param result
     * @return
     */
    @PostMapping("/user/register")
    public CommonResult userRegister(@Validated @RequestBody UserDTO userDTO, BindingResult result){
        //参数传入有误
        if(ValidDataUtil.validData(result)!=null){
            return CommonResultVO.error(ValidDataUtil.validData(result));
        }
        Integer num=userService.userRegister(userDTO);
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

    /**
     * 用户登录
     * @param username
     * @param password
     * @return 成功返回用户名
     */
    @PostMapping ("/user/login")
    public CommonResult userLogin(String username,String password){
        String name=userService.userLogin(username, password);
        if(name==null){
            //登录成功
            return CommonResultVO.success(name);
        }else{
            //登录失败
            return CommonResultVO.error("用户名或密码错误");
        }
    }
    /**
     * 获取短信验证码
     * @param email
     * @return
     */
    @GetMapping("/user/getEmailCode")
    public CommonResult<String> getEmailCode(@RequestParam("email") String email){
        String code=mailUtils.sendEmail(email);
        if(code!=null){
            return CommonResultVO.success(code);
        }else{
            return CommonResultVO.error("验证码发送失败");
        }
    }
}
