package com.github.viqbgrg.springbootoverseer.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author bing
 */
@Data
public class UserSignInDto {
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "邮箱不能为空")
    private String email;
}
