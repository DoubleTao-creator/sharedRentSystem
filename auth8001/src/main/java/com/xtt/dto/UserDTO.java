package com.xtt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * @author xtt
 * @date 2021/1/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotNull(message = "用户名不能为空")
    private String name;
    @NotNull(message = "密码不能为空")
    private String password;
    //@Pattern(regexp = "^1(3|4|5|7|8)d{9}$",message = "手机号格式错误")
    private String tel;
    @Email(message = "非法邮箱地址")
    private String  email;
}
