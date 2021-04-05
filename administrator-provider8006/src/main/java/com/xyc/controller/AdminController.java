package com.xyc.controller;


import com.xyc.dto.AdministratorLoginDTO;
import com.xyc.dto.AdministratorRegisterDTO;
import com.xyc.dto.SlidShowDTO;
import com.xyc.pojo.Administrator;
import com.xyc.pojo.Recommend;
import com.xyc.pojo.SlidShow;
import com.xyc.service.AdministratorService;
import com.xyc.service.RecommendService;
import com.xyc.service.SlidShowService;
import entity.CommonResult;
import entity.CommonResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utils.ValidDataUtil;

import java.util.List;

@RestController
@RequestMapping("/admin/provider")
public class AdminController {

    @Autowired
    private AdministratorService adminService;

    @Autowired
    private RecommendService recommendService;

    @Autowired
    private SlidShowService slidShowService;

    @PostMapping("/adminRegister")
    public CommonResult register(@Validated AdministratorRegisterDTO adminRD,BindingResult result){
        if(ValidDataUtil.validData(result)!=null){
            return CommonResultVO.error(ValidDataUtil.validData(result));
        }
        int i = adminService.register(adminRD);
        if (i>0){
            return CommonResultVO.success("管理员账号注册成功");
        }else {
            return CommonResultVO.error("注册失败");
        }
    }

    @PostMapping("/adminLogin")
    public CommonResult login(@Validated AdministratorLoginDTO adminLD, BindingResult result){
        if(ValidDataUtil.validData(result)!=null){
            return CommonResultVO.error(ValidDataUtil.validData(result));
        }
        Administrator administrator = adminService.login(adminLD);
        if (administrator!=null){
            return CommonResultVO.success(administrator);
        }else {
            return CommonResultVO.error("登录失败");
        }
    }

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

    @GetMapping("/slidshow/delete/{cgoodsId}")
    public CommonResult deleteSlidShow(@PathVariable("cgoodsId") Integer cgoodsId){
        int i = slidShowService.delete(cgoodsId);
        if (i>0){
            return CommonResultVO.success("轮播图删除成功");
        }else{
            return CommonResultVO.error("删除失败");
        }
    }

}
