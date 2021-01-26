package com.xyc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerModifyDTO {

    @NotNull(message = "用户id不能为空或")
    private Integer id;
    @NotNull(message = "用户名不能为空")
    private String name;
    @NotNull(message = "密码不能为空")
    private String password;
    @Pattern(regexp = "^1(3|4|5|7|8)d{9}$",message = "手机号格式错误")
    private String tel;
    @Email(message = "非法邮箱")
    private String email;
    @NotNull(message = "头像不能为空")
    private MultipartFile pic;
    @NotNull(message = "营业许可证不能为空")
    private MultipartFile license;

}
