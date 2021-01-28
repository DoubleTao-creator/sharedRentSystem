package com.xyc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CGoods implements Serializable {
    private Integer id;
    private Integer sellerId;
    private String name;
    private Integer typeId;
    private Integer repertory;
    private String pic;
    private String info;
    private Integer sellModel;
    private Double price;
    private Double rental;
    private Double deposit;
    private String status;
}
