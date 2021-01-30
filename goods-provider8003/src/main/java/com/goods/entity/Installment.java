package com.goods.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author xtt
 * @date 2021/1/30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Installment {
    private Integer id;
    private Integer goodsId;
    private Timestamp startTime;
}
