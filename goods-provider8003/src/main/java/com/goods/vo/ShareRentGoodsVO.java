package com.goods.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShareRentGoodsVO {
    /**
     * 订单开始时间
     */
    private Timestamp startTime;
    /**
     * 押金
     */
    private Double deposit;
    /**
     * 结束时间
     */
    private Timestamp deadTime;
}
