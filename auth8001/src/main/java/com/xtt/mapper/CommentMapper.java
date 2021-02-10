package com.xtt.mapper;

import com.xtt.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CommentMapper {
    /**
     * 用户对商品发表评论
     * @param comment
     * @return
     */
    Integer pushComment(Comment comment);
}
