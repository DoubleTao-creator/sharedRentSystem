package com.xyc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SlidShow {
    private Integer id;
    private Integer cgoodsId;
    private String pic;

    public SlidShow(Integer cgoodsId, String pic) {
        this.cgoodsId = cgoodsId;
        this.pic = pic;
    }
}
