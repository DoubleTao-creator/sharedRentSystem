package com.xyc.controller;

import com.xyc.dto.CGoodsAddDTO;
import com.xyc.service.CGoodsService;
import entity.CommonResult;
import entity.CommonResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.ValidDataUtil;

@RestController
@Validated
@RequestMapping("/cgoods")
public class CGoodsController {

    @Autowired
    private CGoodsService cGoodsService;

    @RequestMapping("/add")
    public CommonResult register(@Validated CGoodsAddDTO cGoodsAD, BindingResult result){
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
}
