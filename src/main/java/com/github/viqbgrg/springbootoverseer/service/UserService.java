package com.github.viqbgrg.springbootoverseer.service;

import com.github.viqbgrg.springbootoverseer.domain.dto.UserSignInDto;
import com.github.viqbgrg.springbootoverseer.entity.User;

public interface UserService {
    User findUserByUsername(String username);

    boolean signIn(UserSignInDto user);
}
