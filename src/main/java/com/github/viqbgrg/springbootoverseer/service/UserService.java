package com.github.viqbgrg.springbootoverseer.service;

import com.github.viqbgrg.springbootoverseer.entity.User;
import com.github.viqbgrg.springbootoverseer.model.Users;

public interface UserService {
    User findUserByUsername(String username);

    boolean signIn(Users user);
}
