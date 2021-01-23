package com.xyc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRecordDTO {

    private Integer goodsId;
    private Integer userId;
    private Integer modleId;
    private Double cost;

}
