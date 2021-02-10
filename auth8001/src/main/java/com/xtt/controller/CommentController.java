package com.xtt.controller;

import com.xtt.dto.CommentDTO;
import com.xtt.service.CommentService;
import entity.CommonResult;
import entity.CommonResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;
    /**
     * 用户对某个商品发表评论
     * @param commentDTO
     * @return
     */
    @PostMapping("/user/publishComment")
    public CommonResult pushComment(@RequestBody CommentDTO commentDTO){
        Integer result=commentService.pushComment(commentDTO);
        if(result==1){
            return CommonResultVO.success(null);
        }else{
            return CommonResultVO.error("评论失败!");
        }
    }

    /**
     * 用户查看某个商品的评论
     * @param cgoodsId 商品id
     * @return 评论信息
     */
    @GetMapping("/user/getComments")
    public CommonResult getComments(Integer cgoodsId){
        return null;
    }
}
