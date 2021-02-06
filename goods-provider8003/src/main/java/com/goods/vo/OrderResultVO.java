package com.goods.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 租赁模式
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResultVO<T> {
    private BaseInformationVO base;
    private T extra;
}
