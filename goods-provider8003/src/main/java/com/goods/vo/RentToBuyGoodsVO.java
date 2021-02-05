package com.goods.vo;

import java.sql.Timestamp;

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
