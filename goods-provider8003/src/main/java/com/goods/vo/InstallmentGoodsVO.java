package com.goods.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstallmentGoodsVO {
    /**
     * 订单开始时间
     */
    private Timestamp startTime;
    /**
     * 下次交租时间
     */
    private Timestamp deadTime;
    /**
     * 剩余待付金额
     */
    private Double notPay;
}
