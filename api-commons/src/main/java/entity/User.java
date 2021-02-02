package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author xtt
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Serializable {
    private Integer id;
    private String name;
    private String password;
    private String  tel;
    private String email;
    private String pic;
    private Integer credit;
    private Double balance;
    private String role;
}
