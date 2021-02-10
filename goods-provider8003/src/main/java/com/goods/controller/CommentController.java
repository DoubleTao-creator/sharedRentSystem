package com.goods.controller;

import com.goods.dto.CommentDTO;
import com.goods.service.CommentService;
import com.goods.vo.CommentVO;
import entity.CommonResult;
import entity.CommonResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    /**
     * 对订单添加评价
     * @param commentDTO
     * @return
     */
    @PostMapping("/goods/publishComment")
    public CommonResult publishComment(CommentDTO commentDTO){
        Integer result=commentService.pushComment(commentDTO);
        if(result==1){
            return CommonResultVO.success(null);
        }else {
            return CommonResultVO.error("评论发表失败");
        }
    }

    /**
     * 根据商品id查看评论
     * @param cgoodsId
     * @return
     */
    @GetMapping("/goods/getComment")
    public CommonResult getComment(Integer cgoodsId){
        List<CommentVO> commentVOS=commentService.getComment(cgoodsId);
        return CommonResultVO.success(commentVOS);
    }
}
