package com.goods.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnedGoodsVO {
    /**
     * 订单id
     */
    private Integer id;
    /**
     * 卖家名称
     */
    private String sellerName;
    /**
     * 卖家id
     */
    private Integer sellerId;
    /**
     * 商品名称
     */
    private String cgoodsName;
    /**
     * 商品id
     */
    private Integer cgoodsId;
    /**
     * 商品图片
     */
    private String cgoodsPic;
    /**
     * 商品描述信息
     */
    private String cgoodsInfo;
    /**
     * 商品价格
     */
    private Double price;
    /**
     * 商品状态
     */
    private String status;
}
