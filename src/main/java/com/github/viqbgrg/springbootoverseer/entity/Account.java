package com.github.viqbgrg.springbootoverseer.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Account {

    private long id;
    private String userId;
    private String nickName;
    private String loginKey;
    private String creditkey;
    private String secureKey;
    private String secure_key;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
