package com.xtt.entity;
/**
 * @author xtt
 * @date 2021/1/13
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Serializable{
    private Integer id;
    private String name;
    private String password;
    private String  tel;
    private String email;
    private String pic;
    private Integer credit;
    private Integer balance;
    private String role;
}
