package com.xyc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRecord implements Serializable {

    private Integer id;
    private Integer goodsId;
    private Integer userId;
    private Integer modelId;
    private Double cost;
    private Date paytime;
    private String info;

}
