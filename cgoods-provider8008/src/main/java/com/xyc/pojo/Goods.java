package com.xyc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {

    /**
     * 对应根据商品类id查询出来的商品数据
     */
    private Integer id;
    private Integer cgoodsId;
    private Integer userId;
    private String status;
    private Integer sellModel;
    private Integer sellId;
}
