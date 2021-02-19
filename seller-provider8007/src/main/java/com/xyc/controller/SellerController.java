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
import org.springframework.web.multipart.MultipartFile;
import utils.ValidDataUtil;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.util.List;

@RestController
@Validated
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    /**
     * 得到商家信息 by id
     * @param id
     * @return
     */
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

    /**
     * 商家信息修改
     * @param sellerMD
     * @return
     * @throws FileNotFoundException
     */
    @PostMapping("/modify")
    public CommonResult modifySeller(SellerModifyDTO sellerMD) throws FileNotFoundException {
        int i = sellerService.modifySeller(sellerMD);
        if (i>0){
            return CommonResultVO.success("商家信息更新成功，请耐心等待管理员的审核");
        }else {
            return CommonResultVO.error("商家信息更新失败");
        }
    }

    /**
     * 商家头像更新
     * @param pic
     * @param id
     * @return
     */
    @PostMapping("/modifyPic/{id}")
    public CommonResult modifyPic(MultipartFile pic,@PathVariable("id") Integer id){
        boolean flag = sellerService.modifyPic(pic,id);
        if (flag){
            return CommonResultVO.success("商家照片更新成功");
        }else {
            return CommonResultVO.error("商家照片更新失败");
        }
    }

    /**
     * 商家营业执照更新
     * @param pic
     * @param id
     * @return
     */
    @PostMapping("/modifyLicense/{id}")
    public CommonResult modifyLicense(MultipartFile pic,@PathVariable("id") Integer id){
        int i = sellerService.modifyLicense(pic,id);
        if (i>0){
            return CommonResultVO.success("商家营业执照更新成功，请耐心等待管理员审核");
        }else {
            return CommonResultVO.error("商家营业执照更新失败");
        }
    }


    /**
     * 登录
     * @param sellerLD
     * @param result
     * @return
     */
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

    /**
     * 商家注册
     * @param sellerRD
     * @param result
     * @return
     */
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

    /**
     * 得到冻结状态的商家
     * @return
     */
    @GetMapping("/frozenAccount")
    public CommonResult<List> getFrozenAccount(){
        List<Seller> list = sellerService.getFrozenAccount();
        if (list.size()>0){
            return CommonResultVO.success(list);
        }else {
            return CommonResultVO.error("暂无处于冻结状态的商家");
        }
    }

    /**
     * 商家认证 修改status为 '正常营业'
     * @param id
     * @return
     */
    @GetMapping("/authenticate/{id}")
    public CommonResult authenticate(@PathVariable("id") int id){
        int i = sellerService.sellerAuthenticate(id);
        if (i>0){
            return CommonResultVO.success("商家信息更新成功");
        }else {
            return CommonResultVO.error("商家信息更新失败");
        }
    }

    /**
     * 修改商家余额
     * @param income
     * @param sellerId
     * @return
     */
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

}
