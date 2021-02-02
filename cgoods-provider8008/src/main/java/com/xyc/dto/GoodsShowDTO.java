package com.xyc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GoodsShowDTO {

    /**
     * 用于商家显示具体商品类下 已被租用商品的状态
     */

    private Integer id;
    private Integer cgoodsId;
    /**
     * 商家查看只需要显示当前租用用户的name
     */
    private String userName;
    /**
     * 当前商品的具体状态sell_id
     */
    private String status;
    /**
     * 租用模式
     */
    private String sellModel;
    /**
     * 按理说不用显示在商家这里
     */
    private Integer sellId;

}
