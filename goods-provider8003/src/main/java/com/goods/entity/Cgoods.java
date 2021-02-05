package com.goods.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xtt
 * 商品类实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cgoods {
    private Integer id;
    /**
     * 卖家ID
     */
    private Integer sellerId;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品类型ID
     */
    private String typeId;
    /**
     * 图片
     */
    private String pic;
    /**
     * 个数
     */
    private String repertory;
    /**
     * 描述信息
     */
    private String info;
    /**
     * 出售模式
     */
    private Integer sellModel;
    /**
     * 价格
     */
    private Double price;
    /**
     * 月租
     */
    private Double rental;
    /**
     * 押金
     */
    private Double deposit;
    /**
     * 状态
     */
    private String status;
}
