package com.goods.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentToBuyGoodsVO {
    /**
     * 订单开始时间
     */
    private Timestamp startTime;
    /**
     * 总体验期
     */
    private Integer totalRentTime;
    /**
     * 体验到期时间
     */
    private Timestamp endTime;
}
