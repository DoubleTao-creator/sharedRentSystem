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
public class SellerRegisterDTO {

    @NotNull(message = "用户名不能为空")
    private String name;
    @NotNull(message = "密码不能为空")
    private String password;
//    @Pattern(regexp = "^1(3|4|5|7|8)d{9}$",message = "手机号格式错误")
    private String tel;
    @Email(message = "非法邮箱地址")
    private String  email;
    @NotNull(message = "请填写营业执照")
    private MultipartFile license;

}
