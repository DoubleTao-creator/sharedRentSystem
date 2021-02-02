package com.goods.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author xtt
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShareRent {
    private Integer id;
    private Double deposit;
    private Integer goodsId;
    private Timestamp startTime;
    private Timestamp deadTime;
}
