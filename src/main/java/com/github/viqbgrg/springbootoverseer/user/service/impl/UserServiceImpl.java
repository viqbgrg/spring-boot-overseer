package com.github.viqbgrg.springbootoverseer.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.viqbgrg.springbootoverseer.exception.UsernameExistException;
import com.github.viqbgrg.springbootoverseer.user.entity.User;
import com.github.viqbgrg.springbootoverseer.user.mapper.UserMapper;
import com.github.viqbgrg.springbootoverseer.user.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author bing
 * @since 2020-12-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User findUserByUsername(String username) {
        return null;
    }

    @Override
    public boolean signIn(User user) {
        String username = user.getUsername();
        List<User> users = this.baseMapper.selectList(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if (!users.isEmpty()) {
            throw new UsernameExistException(username);
        }
        return this.save(user);
    }
}
