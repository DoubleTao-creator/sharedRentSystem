package com.goods.controller;

import com.goods.dto.UserExperienceDTO;
import entity.CommonResult;
import entity.CommonResultVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
/**
 *@author  xtt
 */
@RestController
public class GoodsController {
    public CommonResult ExperienceGoods(@RequestBody UserExperienceDTO userExperienceDTO){

        return CommonResultVO.success(null);
    }
}
