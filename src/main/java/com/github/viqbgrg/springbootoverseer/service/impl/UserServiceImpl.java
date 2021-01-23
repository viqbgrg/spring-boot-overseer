package com.github.viqbgrg.springbootoverseer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.viqbgrg.springbootoverseer.entity.User;
import com.github.viqbgrg.springbootoverseer.exception.UsernameExistException;
import com.github.viqbgrg.springbootoverseer.mapper.UserMapper;
import com.github.viqbgrg.springbootoverseer.service.IUserService;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
        User user = this.baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        return user;
    }

    @Override
    public boolean signIn(User user) {
        String username = user.getUsername();
        User user1 = findUserByUsername(username);
        if (user1 != null) {
            throw new UsernameExistException(username);
        }
        DefaultPasswordService defaultPasswordService = new DefaultPasswordService();
        String s = defaultPasswordService.encryptPassword(user.getPassword());
        user.setPassword(s);
        user.setCreateAt(LocalDateTime.now());
        user.setLocked(false);
        return this.save(user);
    }
}
