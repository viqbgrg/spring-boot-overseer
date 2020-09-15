package com.github.viqbgrg.springbootoverseer.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author nnj
 */
@Data
public class User {
    @NotNull
    @Size(min = 6)
    private String username;
    @NotNull
    @Size(min = 8, message = "密码不能小于6位")
    @Size(max = 64, message = "密码不能小于6位")
    private String password;
    @NotNull
    private String salt;
}
