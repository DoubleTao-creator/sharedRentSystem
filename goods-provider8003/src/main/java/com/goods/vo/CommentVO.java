package com.goods.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户头像
     */
    private String userPic;
    /**
     * 用户评论
     *
     */
    private String remark;
    /**
     * 评论图片
     */
    private String remarkPic;
    /**
     * 时间
     */
    private Timestamp time;
}
