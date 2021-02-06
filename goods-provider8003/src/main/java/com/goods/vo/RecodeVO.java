package com.goods.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * @author xtt
 */
public class RecodeVO {
    private String goodsName;
    private String goodsPic;
    private String userName;
    private Double cost;
    private Timestamp paytime;
    private String info;
    private String model;
}
