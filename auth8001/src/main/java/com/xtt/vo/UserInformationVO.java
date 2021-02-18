package com.xtt.vo;


import com.xtt.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInformationVO {
    private User user;
    private String tokrn;
}
