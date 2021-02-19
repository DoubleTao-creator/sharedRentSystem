package com.xtt.dto;
/**
 * @author xtt
 */

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
public class ModifyUserDTO {
    @NotNull(message = "获取不到用户id")
    private Integer id;
    @NotNull(message = "用户名不能为空")
    private String name;
   // @Pattern(regexp = "^1(3|4|5|7|8)d{9}$",message = "手机号格式错误")
    private String tel;
    @NotNull(message = "头像不能为空")
    private MultipartFile file;
    @Email(message = "非法邮箱地址")
    private String  email;
}
