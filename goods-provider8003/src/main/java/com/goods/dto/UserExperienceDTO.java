package com.goods.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserExperienceDTO {
    private Integer userId;
    private Integer cgoodsId;
    private String  sellModel;
    private Integer rentTime;
}
