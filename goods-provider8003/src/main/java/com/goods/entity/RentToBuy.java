package com.goods.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author xtt
 * 先租后买实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentToBuy {
    private Integer id;
    private Integer goodsId;
    private Integer rentTime;
    private Timestamp startTime;
}
