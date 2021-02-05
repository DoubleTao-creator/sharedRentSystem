package com.goods.vo;

import java.sql.Timestamp;

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
