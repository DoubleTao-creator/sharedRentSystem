package com.xyc.controller;

import com.xyc.dto.CGoodsAddDTO;
import com.xyc.dto.CGoodsModifyDTO;
import com.xyc.dto.CGoodsShowDTO;
import com.xyc.dto.GoodsShowDTO;
import com.xyc.pojo.CGoods;
import com.xyc.pojo.Seller;
import com.xyc.service.CGoodsService;
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

import java.util.List;

@RestController
@Validated
@RequestMapping("/cgoods")
public class CGoodsController {

    @Autowired
    private CGoodsService cGoodsService;

    @Autowired
    private SellerService sellerService;
    /**
     * 添加商品类
     * @param cGoodsAD
     * @param result
     * @return
     */
    @PostMapping("/add")
    public CommonResult add(@Validated CGoodsAddDTO cGoodsAD, BindingResult result){
        if(ValidDataUtil.validData(result)!=null){
            return CommonResultVO.error(ValidDataUtil.validData(result));
        }
        int i = cGoodsService.add(cGoodsAD);
        if (i>0){
            return CommonResultVO.success("商品添加成功！请耐心等待管理员审核");
        }else if (i==-100){
            return CommonResultVO.error("账户还未通过审核，无法上架商品，请耐心等待管理员审核");

        }else{
            return CommonResultVO.error("添加失败");
        }
    }

    /**
     * 修改商品类的信息
     * @param cGoodsMD
     * @param result
     * @return
     */
    @PostMapping("/modify")
    public CommonResult modify(@Validated CGoodsModifyDTO cGoodsMD,BindingResult result){
        if(ValidDataUtil.validData(result)!=null){
            return CommonResultVO.error(ValidDataUtil.validData(result));
        }
        int i = cGoodsService.updateInfo(cGoodsMD);
        if (i>0){
            return CommonResultVO.success("商品信息更新成功！请耐心等待管理员审核");
        }else {
            return CommonResultVO.error("商品信息更新失败");

        }
    }

    /**
     * 更新照片
     * @return
     */
    @PostMapping("/modifyPic")
    public CommonResult modifyPic(MultipartFile pic,Integer id){
        System.out.println(id);
        boolean flag = cGoodsService.updatePic(pic,id);

        if (flag){
            return CommonResultVO.success("商品照片更新成功！请耐心等待管理员审核");
        }else {
            return CommonResultVO.error("商品照片更新失败");
        }

    }


    /**
     * 根据商家id得到商品类
     * @param sellerId 商家id
     * @return
     */
    @GetMapping("/seller/getCGoods/{sellerId}")
    public CommonResult<List> getSellerOwnCGoods(@PathVariable("sellerId") Integer sellerId){
        List<CGoodsShowDTO> list = cGoodsService.searchBySellerId(sellerId);

        if (list.size()==0){
            return CommonResultVO.success("您还没有上传任何商品");
        }else {
            return CommonResultVO.success(list);
        }
    }

    /**
     * 得到某个商品类下的所有单个商品的状态和使用情况
     * @param cGoodsId 商品类
     * @return
     */
    @GetMapping("/seller/getCGoods/getEachGoods/{cGoodsId}")
    public CommonResult<List> getEachGoodsByCGoodId(
            @PathVariable("cGoodsId") Integer cGoodsId){
        List<GoodsShowDTO> list = cGoodsService.getEachGoodsByCGoodId(cGoodsId);
        if (list.size() == 0){
            return CommonResultVO.success("当前商品类还没有任何人租用");
        }else {
            return CommonResultVO.success(list);
        }
    }

    /**
     * 得到某个商品类
     * （适用于用户查看某类商品的具体信息）
     * @param id 商品类id
     * @return
     */
    @GetMapping("/getCGoods/{id}")
    public CommonResult getOneCGoods(@PathVariable("id") Integer id){
        if (id<=0){
            return CommonResultVO.error("此类商品不存在");
        }
        CGoodsShowDTO cGoodSD =  cGoodsService.getById(id);
        return CommonResultVO.success(cGoodSD);
    }

    /**
     * 得到某种类型下的商品
     * @param typeId
     * @return
     */
    @GetMapping("/type/getCGoods/{typeId}")
    public CommonResult<List> getCGoodsByTypeId(@PathVariable("typeId") Integer typeId){
        List<CGoodsShowDTO> list = cGoodsService.searchByTypeId(typeId);
        if (list.size() == 0){
            return CommonResultVO.success("还没有当前类型的商品");
        }else {
            return CommonResultVO.success(list);
        }
    }

    @GetMapping("/getCGoodsToApprove")
    public CommonResult<List> getCGoodsByStatus(){
        List<CGoodsShowDTO> list = cGoodsService.getByStatus();
        if (list.size() == 0){
            return CommonResultVO.success("当前没有待审核的商品");
        }else {
            return CommonResultVO.success(list);
        }
    }

    @GetMapping("/approve/{cgoodsId}")
    public CommonResult approveCGoods(@PathVariable("cgoodsId") Integer cgoodsId){
        int i = cGoodsService.changeStatus(cgoodsId);
        if (i<=0) {
            return CommonResultVO.error("商品审核未通过");
        }else {
            //给商家发送邮件，告知商品审核已通过
            CGoods cGoods = cGoodsService.getById2(cgoodsId);
            Seller seller = sellerService.queryById(cGoods.getSellerId());
            mailSenderImp(seller,cGoods);
            return CommonResultVO.success("商品类:"+cgoodsId+" 审核通过");
        }
    }

    @Autowired
    JavaMailSenderImpl mailSender;

    private void mailSenderImp(Seller seller, CGoods cGoods){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("极限一带四租赁平台商品审核结果来信");
        mailMessage.setText("亲爱的"+seller.getName()
                +", 您的商品"+cGoods.getName()
                +"（商品id："+cGoods.getId()+"）"
                +" 已经通过平台审核");
        mailMessage.setTo(seller.getEmail());
        mailMessage.setFrom("1830069482@qq.com");

        mailSender.send(mailMessage);
    }



}
