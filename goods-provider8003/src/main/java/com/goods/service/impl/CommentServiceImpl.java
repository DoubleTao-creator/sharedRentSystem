package com.goods.service.impl;

import com.goods.dto.CommentDTO;
import com.goods.mapper.CommentMapper;
import com.goods.mapper.GoodsMapper;
import com.goods.mapper.UserMapper;
import com.goods.service.CommentService;
import com.goods.vo.CommentVO;
import entity.Comment;
import entity.FTPConstants;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.PhotoUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    UserMapper userMapper;
    /**
     * 用户对订单添加评论
     * @param commentDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer pushComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setCgoodsId(goodsMapper.findGoodsById(commentDTO.getGoodsId()).getCgoodsId());
        comment.setRemark(comment.getRemarkPic());
        comment.setUserId(commentDTO.getUserId());
        if(commentDTO.getRemarkPic()==null){
            //用户没有上传图片
            comment.setRemarkPic(null);
            commentMapper.pushComment(comment);
            return 1;
        }else {
            //用户上传了图片
            try {
                PhotoUtils.uploadPic(commentDTO.getRemarkPic(), PhotoUtils.BASE_HEAD_PHOTO_URL + PhotoUtils.COMMENT_PREFIX + commentDTO.getRemarkPic().getName() + PhotoUtils.SUFFIX);
                comment.setRemarkPic(PhotoUtils.BASE_HEAD_PHOTO_URL + PhotoUtils.COMMENT_PREFIX + commentDTO.getRemarkPic().getName() + PhotoUtils.SUFFIX);
                //添加到数据库
                commentMapper.pushComment(comment);
                return 1;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<CommentVO> getComment(Integer cgoodsId) {
        List<Comment> comments=commentMapper.getComment(cgoodsId);
        List<CommentVO> commentVOS=new ArrayList<>();
        if(comments==null){
            return null;
        }else {
            for(Comment comment:comments){
                CommentVO commentVO=new CommentVO();
                commentVO.setRemark(comment.getRemark());
                commentVO.setRemarkPic(comment.getRemarkPic());
                User user=userMapper.findUserById(comment.getUserId());
                commentVO.setUserName(user.getName());
                commentVO.setUserPic(user.getPic());
                commentVO.setTime(comment.getTime());
                commentVOS.add(commentVO);
            }
            return commentVOS;
        }
    }
}
