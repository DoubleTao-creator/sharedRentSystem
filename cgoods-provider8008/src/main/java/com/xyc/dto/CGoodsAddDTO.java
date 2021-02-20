package com.xyc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CGoodsAddDTO implements Serializable {

    @NotNull(message = "商家id不能为空")
    private Integer sellerId;
    @NotNull(message = "商品名不能为空")
    private String name;
    @NotNull(message = "商品类型不能为空")
    private Integer typeId;
    @NotNull(message = "商品库存不能为空")
    private Integer repertory;
//    @NotNull(message = "商品图片不能为空")
    private MultipartFile pic;
    @NotNull(message = "商品信息不能为空")
    private String info;
    @NotNull(message = "商品可支持的租赁模式不能为空")
    private String[] sellModels;
    @NotNull(message = "商品价格不能为空")
    private Double price;
    @NotNull(message = "商品租金不能为空")
    private Double rental;
    @NotNull(message = "商品押金不能为空")
    private Double deposit;

}
