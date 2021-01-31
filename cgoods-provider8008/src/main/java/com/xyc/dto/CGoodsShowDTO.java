package com.xyc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CGoodsShowDTO {

    private Integer id;
    private Integer sellerId;
    private String name;
    private String type;
    private Integer repertory;
    private String pic;
    private String info;
    private String[] sellModels;
    private Double price;
    private Double rental;
    private Double deposit;
    private String status;

}
