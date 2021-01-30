package com.goods.entity;
/**
 * @author xtt
 */
/**
 * 商品实体类
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    /**
     * 商品ID
     */
    private Integer id;
    /**
     * 所属商品类ID
     */
    private Integer cgoodsId;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 商品状态
     */
    private String status;
    /**
     * 出售模式
     */
    private Integer sellModel;
    /**
     * 当前出售模式记录
     */
    private Integer sellId;
}
