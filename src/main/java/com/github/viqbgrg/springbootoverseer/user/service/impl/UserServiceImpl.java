package com.github.viqbgrg.springbootoverseer.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.viqbgrg.springbootoverseer.exception.UsernameExistException;
import com.github.viqbgrg.springbootoverseer.user.entity.User;
import com.github.viqbgrg.springbootoverseer.user.mapper.UserMapper;
import com.github.viqbgrg.springbootoverseer.user.service.IUserService;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.springframework.stereotype.Service;

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
        return this.save(user);
    }
}