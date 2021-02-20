package com.xtt.controller;
/**
 * @Author xtt
 * @date 2021/1/20
 */
import com.xtt.dto.ModifyUserDTO;
import com.xtt.dto.PassWordDTO;
import com.xtt.dto.UserDTO;
import com.xtt.entity.User;
import com.xtt.service.UserService;
import com.xtt.util.MailUtils;
import com.xtt.vo.UserInformationVO;
import entity.CommonResult;
import entity.CommonResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utils.JwtUtils;
import utils.ValidDataUtil;

import java.util.HashMap;
import java.util.Map;

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
        System.out.println(userDTO.getName());
        System.out.println(userDTO.getPassword());
        System.out.println(userDTO.getEmail());
        System.out.println(userDTO.getTel());
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
     * @return 成功返回用户信息
     */
    @PostMapping ("/user/login")
    public CommonResult userLogin(String username,String password){
        System.out.println("用户"+username+"请求登录");
        User user=userService.userLogin(username, password);
        if(user!=null){
            //登录成功
            //生成token
            String token= JwtUtils.generateToken(user.getId().toString(), user.getRole());
            UserInformationVO userInformationVO=new UserInformationVO();
            userInformationVO.setUserId(user.getId());
            userInformationVO.setToken(token);
            //返回用户信息及token
            return CommonResultVO.success(userInformationVO);
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
    @GetMapping("/user/getEmailCode/{email}")
    public CommonResult<String> getEmailCode(@PathVariable("email") String email){
        String code=mailUtils.sendEmail(email);
        if(code!=null){
            return CommonResultVO.success(code);
        }else{
            return CommonResultVO.error("验证码发送失败");
        }
    }

    /**
     * 更改用户基本信息（头像，tel, email,username)
     * @param modifyUserDTO
     * @param bindingResult
     * @return
     */
    @PostMapping("/user/modifyUser")
    public CommonResult modifyUser(@Validated ModifyUserDTO modifyUserDTO,BindingResult bindingResult){
        if(ValidDataUtil.validData(bindingResult)!=null){
            //参数传入有误
            return CommonResultVO.error(ValidDataUtil.validData(bindingResult));
        }
        //更改用户信息
        User user=userService.modifyUser(modifyUserDTO);
        if(user!=null){
            //更改成功，返回更改后的用户信息
            return CommonResultVO.success(user);
        }else {
            //更改失败
            return CommonResultVO.error("用户信息更改失败");
        }
    }

    /**
     * 修改用户密码
     * @param passWordDTO 用户id,原密码，新密码
     * @return
     */
    @PostMapping("/user/modifyUserPassword")
    public CommonResult modifyUserPassword(PassWordDTO passWordDTO){
        Integer result=userService.modifyUserPassword(passWordDTO);
        if(result==0){
            return CommonResultVO.error("原密码输入有误");
        }else if(result==1){
            //修改成功
            return CommonResultVO.success(null);
        }else {
            return CommonResultVO.error("修改成功");
        }
    }
    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    @GetMapping("/user/getUserInfo/{id}")
    public CommonResult getUserInfo(@PathVariable("id") Integer id){
        User user=userService.findUserById(id);
        if(user==null) {
            return CommonResultVO.error("不存在该用户");
        }else{
            return CommonResultVO.success(user);
        }
    }

    /**
     * 用户充值
     * @param userId 用户id
     * @param money 充值金额
     * @return
     */
    @PostMapping("/user/reCharge")
    public CommonResult reCharge(Integer userId,Double money){
        Integer result=userService.reCharge(userId, money);
        if(result==1){
            return CommonResultVO.success(null);
        }else {
            return CommonResultVO.error("充值失败");
        }
    }
}
