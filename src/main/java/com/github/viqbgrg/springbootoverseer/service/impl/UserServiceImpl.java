package com.github.viqbgrg.springbootoverseer.service.impl;

import com.github.viqbgrg.springbootoverseer.entity.User;
import com.github.viqbgrg.springbootoverseer.mapper.UsersMapper;
import com.github.viqbgrg.springbootoverseer.model.Users;
import com.github.viqbgrg.springbootoverseer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public User findUserByUsername(String username) {
        return null;
    }

    @Override
    public boolean signIn(Users user) {
        usersMapper.insert(user);
        return false;
    }
}
