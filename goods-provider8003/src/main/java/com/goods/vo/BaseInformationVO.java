package com.goods.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * @author xtt
 */
public class BaseInformationVO {
    private Integer sellerId;
    private String sellerName;
    private Integer cgoodsId;
    private Integer goodsId;
    private String cgoodsName;
    private String  cgoodsPic;
    private Double cgoodsPrice;
    private String status;
    private String model;
}
