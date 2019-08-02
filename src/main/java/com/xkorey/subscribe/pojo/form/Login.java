package com.xkorey.subscribe.pojo.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class Login {

    @Email
    @NotNull(message = "请输入邮箱")
    private String  userName;

    @Length(min = 4, max=48,message = "密码长度为4-16位")
    @NotNull(message = "请输入密码")
//    @Pattern(regexp = "^((19[0-9])|(14[0-9])|(13[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9]))\\d{8}$",message = "密码格式不正确")
    private String passwd;

    private String remember;

}
