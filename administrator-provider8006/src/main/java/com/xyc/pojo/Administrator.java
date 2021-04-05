package com.xyc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Administrator implements Serializable {

    private int id;
    private String name;
    private String password;
    private String role;


}
