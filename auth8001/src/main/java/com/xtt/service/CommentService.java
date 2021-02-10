package com.xtt.service;

import com.xtt.dto.CommentDTO;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    Integer pushComment(CommentDTO commentDTO);
}
