package com.xyc.controller;

import com.xyc.dto.SellerLoginDTO;
import com.xyc.dto.SellerModifyDTO;
import com.xyc.dto.SellerRegisterDTO;
import com.xyc.pojo.Seller;
import com.xyc.service.SellerService;
import entity.CommonResult;
import entity.CommonResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
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
    @GetMapping("/getInfo/{id}")
    public CommonResult getInfo(@PathVariable("id") int id){
        Seller seller = sellerService.queryById(id);
        System.out.println(seller);
        if (seller==null){
            return CommonResultVO.error("请重新登录");
        }else{
            return CommonResultVO.success(seller);
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
    @PostMapping("/modifyPic")
    public CommonResult modifyPic(MultipartFile pic,Integer id){
        boolean flag = sellerService.modifyPic(pic,id);
        if (flag){
            return CommonResultVO.success("商家照片更新成功");
        }else {
            return CommonResultVO.error("商家照片更新失败");
        }
    }

    /**
     * 商家营业执照更新
     * @param license
     * @param id
     * @return
     */
    @PostMapping("/modifyLicense")
    public CommonResult modifyLicense(MultipartFile license,Integer id){
        boolean flag = sellerService.modifyLicense(license,id);
        if (flag){
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
            Seller seller = sellerService.queryById(id);
            mailSenderImp(seller);
            return CommonResultVO.success("已通过该商家的认证");
        }else {
            return CommonResultVO.error("商家认证失败");
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

    @Autowired
    JavaMailSenderImpl mailSender;

    private void mailSenderImp(Seller seller){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("极限一带四租赁平台商品审核结果来信");
        mailMessage.setText("亲爱的"+seller.getName()
                +", 您的店铺已经通过平台审核, 欢迎加入我们!");
        mailMessage.setTo(seller.getEmail());
        mailMessage.setFrom("1830069482@qq.com");

        mailSender.send(mailMessage);
    }
}
