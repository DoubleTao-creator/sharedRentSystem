package com.goods.service;

import com.goods.dto.CommentDTO;
import com.goods.vo.CommentVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    /**
     * 用户对订单评价
     * @param commentDTO
     * @return
     */
    Integer pushComment(CommentDTO commentDTO);

    /**
     * 根据商品类id查看商品评论
     * @param cgoodsId
     * @return
     */
    List<CommentVO> getComment(Integer cgoodsId);
}
