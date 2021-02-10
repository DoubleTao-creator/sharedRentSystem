package com.goods.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Integer userId;
    private Integer goodsId;
    private MultipartFile remarkPic;
    private String remark;
}
