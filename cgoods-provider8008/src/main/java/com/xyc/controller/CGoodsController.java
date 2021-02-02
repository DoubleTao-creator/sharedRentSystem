package com.xyc.controller;

import com.xyc.dto.CGoodsAddDTO;
import com.xyc.dto.CGoodsModifyDTO;
import com.xyc.dto.CGoodsShowDTO;
import com.xyc.dto.GoodsShowDTO;
import com.xyc.pojo.CGoods;
import com.xyc.service.CGoodsService;
import entity.CommonResult;
import entity.CommonResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utils.ValidDataUtil;

import java.util.List;

@RestController
@Validated
@RequestMapping("/cgoods")
public class CGoodsController {

    @Autowired
    private CGoodsService cGoodsService;

    @PostMapping("/add")
    public CommonResult add(@Validated CGoodsAddDTO cGoodsAD, BindingResult result){
        if(ValidDataUtil.validData(result)!=null){
            return CommonResultVO.error(ValidDataUtil.validData(result));
        }
        int i = cGoodsService.add(cGoodsAD);
        if (i>0){
            return CommonResultVO.success("商品添加成功！请耐心等待管理员审核");
        }else {
            return CommonResultVO.error("添加失败");

        }
    }

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

    @GetMapping("/getCGoods/{sellerId}")
    public CommonResult<List> getSellerOwnCGoods(@PathVariable("sellerId") Integer sellerId){
        List<CGoodsShowDTO> list = cGoodsService.searchBySellerId(sellerId);

        if (list.size()==0){
            return CommonResultVO.success("您还没有上传任何商品");
        }else {
            return CommonResultVO.success(list);
        }
    }

    @GetMapping("/getCGoods/getEachGoods/{cGoodsId}")
    public CommonResult<List> getEachGoodsByCGoodId(
            @PathVariable("cGoodsId") Integer cGoodsId){
        List<GoodsShowDTO> list = cGoodsService.getEachGoodsByCGoodId(cGoodsId);
        if (list.size() == 0){
            return CommonResultVO.success("当前商品类还没有任何人租用");
        }else {
            return CommonResultVO.success(list);
        }
    }


}
