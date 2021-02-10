package com.goods.mapper;

import entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CommentMapper {

    Integer pushComment(Comment comment);
    List<Comment> getComment(Integer cgoodsId);
}
