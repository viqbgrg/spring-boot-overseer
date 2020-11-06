package com.github.viqbgrg.springbootoverseer.domain.dto;

import lombok.Data;

@Data
public class UserSignInDto {
    private String username;
    private String password;
    private String email;
}
