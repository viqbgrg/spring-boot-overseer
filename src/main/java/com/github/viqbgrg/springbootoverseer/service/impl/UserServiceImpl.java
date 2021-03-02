package com.github.viqbgrg.springbootoverseer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.viqbgrg.springbootoverseer.entity.Account;
import com.github.viqbgrg.springbootoverseer.entity.User;
import com.github.viqbgrg.springbootoverseer.entity.UserAccount;
import com.github.viqbgrg.springbootoverseer.exception.UsernameExistException;
import com.github.viqbgrg.springbootoverseer.mapper.UserMapper;
import com.github.viqbgrg.springbootoverseer.service.IAccountService;
import com.github.viqbgrg.springbootoverseer.service.IUserAccountService;
import com.github.viqbgrg.springbootoverseer.service.IUserService;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    private IUserAccountService userAccountService;
    private IAccountService accountService;

    public UserServiceImpl(IUserAccountService userAccountService, IAccountService accountService) {
        this.userAccountService = userAccountService;
        this.accountService = accountService;
    }

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

    @Override
    public List<Account> getAllAccount(Long userId) {
        List<UserAccount> allAccount = userAccountService.getAllAccount(userId);
        List<Long> collect = allAccount.stream().map(account -> account.getUserId()).collect(Collectors.toList());
        List<Account> accountList = accountService.getAccountList(collect);
        return accountList;
    }

}
