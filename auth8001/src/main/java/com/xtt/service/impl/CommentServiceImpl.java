package com.xtt.service.impl;

import com.xtt.dto.CommentDTO;
import com.xtt.entity.Comment;
import com.xtt.mapper.CommentMapper;
import com.xtt.service.CommentService;
import entity.FTPConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.PhotoUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author xtt
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public Integer pushComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setGoodsId(commentDTO.getGoodsId());
        comment.setRemark(comment.getRemarkPic());
        comment.setRemarkPic(PhotoUtils.BASE_HEAD_PHOTO_URL + PhotoUtils.COMMENT_PREFIX + commentDTO.getRemarkPic().getName() + PhotoUtils.SUFFIX);
        comment.setUserId(commentDTO.getUserId());
        if(comment.getRemarkPic()==null){
            //用户没有上传图片
            comment.setRemarkPic(null);
            commentMapper.pushComment(comment);
            return 1;
        }else {
            //用户上传了图片
            try {
                FTPConstants ftpConstants = new FTPConstants();
                ftpConstants.setFilename(PhotoUtils.COMMENT_PREFIX + commentDTO.getRemarkPic().getName() + PhotoUtils.SUFFIX);
                ftpConstants.setInput(new FileInputStream(PhotoUtils.transferToFile(commentDTO.getRemarkPic())));
                //上传图片
                PhotoUtils.uploadFile(ftpConstants);
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
}
