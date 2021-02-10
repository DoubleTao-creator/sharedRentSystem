package com.xtt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    @NotNull(message = "没有指定用户信息")
    private Integer userId;
    @NotNull(message = "没有指定订单信息")
    private Integer goodsId;
    private String remark;
    private MultipartFile remarkPic;
}
