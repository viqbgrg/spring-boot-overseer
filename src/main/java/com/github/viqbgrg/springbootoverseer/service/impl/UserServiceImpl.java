package com.github.viqbgrg.springbootoverseer.service.impl;

import com.github.viqbgrg.springbootoverseer.mapper.UserMapper;
import com.github.viqbgrg.springbootoverseer.model.User;
import com.github.viqbgrg.springbootoverseer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByUsername(String username) {
        return null;
    }

    @Override
    public boolean signIn(User user) {
        return userMapper.insert(user) == 1;
    }
}
