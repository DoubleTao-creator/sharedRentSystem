package com.goods.service.impl;

import com.goods.dto.CommentDTO;
import com.goods.mapper.CommentMapper;
import com.goods.mapper.GoodsMapper;
import com.goods.mapper.UserMapper;
import com.goods.service.CommentService;
import com.goods.vo.CommentVO;
import entity.Comment;
import entity.FTPConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
                FTPConstants ftpConstants = new FTPConstants();
                ftpConstants.setFilename(PhotoUtils.COMMENT_PREFIX + commentDTO.getRemarkPic().getName() + PhotoUtils.SUFFIX);
                ftpConstants.setInput(new FileInputStream(PhotoUtils.MultipartFileToFile(commentDTO.getRemarkPic())));
                //上传图片
                PhotoUtils.uploadFile(ftpConstants);
                comment.setRemarkPic(PhotoUtils.BASE_HEAD_PHOTO_URL + PhotoUtils.COMMENT_PREFIX + commentDTO.getRemarkPic().getName() + PhotoUtils.SUFFIX);
                //添加到数据库
                commentMapper.pushComment(comment);
                return 1;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

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
                com.xtt.entity.User user=userMapper.findUserById(comment.getUserId());
                commentVO.setUserName(user.getName());
                commentVO.setUserPic(user.getPic());
                commentVO.setTime(comment.getTime());
                commentVOS.add(commentVO);
            }
            return commentVOS;
        }
    }
}
