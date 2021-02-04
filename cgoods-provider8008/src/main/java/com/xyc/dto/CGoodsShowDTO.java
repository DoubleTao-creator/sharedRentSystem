package com.xyc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CGoodsShowDTO {
    /**
     * 用于前端展示
     */

    private Integer id;

    private Integer sellerId;

    private String name;

    /**
     * typeId商品种类变成为文字
     */
    private String type;

    private Integer repertory;

    private String pic;

    private String info;

    /**
     * 支持的出售模式（复选框）
     */
    private String[] sellModels;

    private Double price;

    private Double rental;

    private Double deposit;

    private String status;

}
