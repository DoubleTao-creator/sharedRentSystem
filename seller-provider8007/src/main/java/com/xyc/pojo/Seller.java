package com.xyc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.IntrospectionException;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seller implements Serializable{

    private Integer id;
    private String name;
    private String password;
    private String tel;
    private String email;
    private String pic;
    private Integer balance;
    private String license;
    private String status;

}
