package com.github.viqbgrg.springbootoverseer.service;

import com.github.viqbgrg.springbootoverseer.model.User;

public interface UserService {
    User findUserByUsername(String username);

    boolean signIn(User user);
}
