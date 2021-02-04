package com.xyc.controller;

import com.xyc.dto.CGoodsAddDTO;
import com.xyc.dto.CGoodsModifyDTO;
import com.xyc.dto.CGoodsShowDTO;
import com.xyc.dto.GoodsShowDTO;
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
        }else {
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
     * 根据商家id得到商品类
     * @param sellerId 商家id
     * @return
     */
    @GetMapping("/getCGoods/{sellerId}")
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
    @GetMapping("/getCGoods/{typeId}")
    public CommonResult<List> getCGoodsByTypeId(@PathVariable("typeId") Integer typeId){
        List<CGoodsShowDTO> list = cGoodsService.searchByTypeId(typeId);
        if (list.size() == 0){
            return CommonResultVO.success("还没有当前类型的商品");
        }else {
            return CommonResultVO.success(list);
        }
    }


}
