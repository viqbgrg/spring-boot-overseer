package com.github.viqbgrg.springbootoverseer.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserSignInDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
}
