package com.github.viqbgrg.springbootoverseer.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author bing
 */
@Data
@ApiModel(value = "LoginParams", description="登陆实体类")
public class UserLoginDto {
    @ApiModelProperty(value = "账号", required = true)
    private String username;
    @ApiModelProperty(value = "密码", required = true)
    private String password;
}
