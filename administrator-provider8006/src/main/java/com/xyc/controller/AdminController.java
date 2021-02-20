package com.xyc.controller;


import com.xyc.dto.SlidShowDTO;
import com.xyc.pojo.Recommend;
import com.xyc.pojo.SlidShow;
import com.xyc.service.RecommendService;
import com.xyc.service.SlidShowService;
import entity.CommonResult;
import entity.CommonResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/provider")
public class AdminController {

    @Autowired
    private RecommendService recommendService;

    @Autowired
    private SlidShowService slidShowService;

    @GetMapping("/recommend/get")
    public CommonResult<List> getRcmd(){
        List<Recommend> list = recommendService.get();
        if (list.size()>0){
            return CommonResultVO.success(list);
        }else {
            return CommonResultVO.error("暂无商品推荐");
        }
    }

    @PostMapping("/recommend/add")
    public CommonResult addRcmd(Integer cGoodsId){
        int i = recommendService.add(cGoodsId);
        if (i>0){
            return CommonResultVO.success("成功加入推荐列表");
        }else{
            return CommonResultVO.error("失败");
        }
    }

    @GetMapping("/recommend/delete/{id}")
    public CommonResult deleteRcmd(@PathVariable("id") Integer id){
        int i = recommendService.delete(id);
        if (i>0){
            return CommonResultVO.success("成功删除");
        }else{
            return CommonResultVO.error("失败");
        }
    }

    @GetMapping("/slidshow/get")
    public CommonResult<List> getSlidShow(){
        List<SlidShow> list = slidShowService.get();
        if (list.size()>0){
            return CommonResultVO.success(list);
        }else {
            return CommonResultVO.error("暂无轮播图");
        }
    }

    @PostMapping("/slidshow/add")
    public CommonResult addSlidShow(SlidShowDTO showDTO){
        int i = slidShowService.add(showDTO);
        if (i>0){
            return CommonResultVO.success("轮播图添加成功");
        }else{
            return CommonResultVO.error("添加失败");
        }
    }

    @GetMapping("/slidshow/delete/{id}")
    public CommonResult deleteSlidShow(@PathVariable("id") Integer id){
        int i = slidShowService.delete(id);
        if (i>0){
            return CommonResultVO.success("轮播图删除成功");
        }else{
            return CommonResultVO.error("删除失败");
        }
    }

}
