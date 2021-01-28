package com.xyc.controller;

import com.xyc.dto.SellerLoginDTO;
import com.xyc.dto.SellerModifyDTO;
import com.xyc.dto.SellerRegisterDTO;
import com.xyc.pojo.Seller;
import com.xyc.service.SellerService;
import entity.CommonResult;
import entity.CommonResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utils.ValidDataUtil;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.FileNotFoundException;

@RestController
@Validated
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @PostMapping("/modifyBalance/{sellerId}/{income}")
    public CommonResult modifyBalance(@PathVariable("income") int income,
                                      @PathVariable("sellerId") int sellerId){
        int i = sellerService.updateBalance(income,sellerId);
        if (i>0){
            return CommonResultVO.success("已到账金额"+income);
        }else{
            return CommonResultVO.error("收钱失败");
        }
    }

    @PostMapping("getInfo/{id}")
    public CommonResult getInfo(@PathVariable("id") int id){
        Seller seller = sellerService.queryById(id);
        System.out.println(seller);
        if (seller==null){
            return CommonResultVO.error("请重新登录");
        }else{
            return CommonResultVO.success("个人消息已得到");
        }
    }

    @PostMapping("/modify")
    public CommonResult modifySeller(SellerModifyDTO sellerMD) throws FileNotFoundException {
        Seller seller = sellerService.modifySeller(sellerMD);
        if (seller!=null){
            return CommonResultVO.success(seller);
        }else {
            return CommonResultVO.error("信息更新失败");
        }
    }

    @PostMapping("/login")
    public CommonResult login(@Validated SellerLoginDTO sellerLD,BindingResult result){
        if (ValidDataUtil.validData(result)!=null){
            return CommonResultVO.error(ValidDataUtil.validData(result));
        }
        Seller seller = sellerService.login(sellerLD);
        if (seller==null){
            return CommonResultVO.error("用户名或密码错误");
        }else {
            return CommonResultVO.success(seller);
        }
    }

    @PostMapping("/register")
    public CommonResult register(@Validated SellerRegisterDTO sellerRD, BindingResult result){

        if(ValidDataUtil.validData(result)!=null){
            return CommonResultVO.error(ValidDataUtil.validData(result));
        }
        int i = sellerService.register(sellerRD);
        if (i>0){
            return CommonResultVO.success("注册成功！请耐心等待管理员审核");
        }else {
            return CommonResultVO.error("注册失败");

        }

    }

}
