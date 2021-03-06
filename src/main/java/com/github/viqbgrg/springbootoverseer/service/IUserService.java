package com.github.viqbgrg.springbootoverseer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.viqbgrg.springbootoverseer.entity.Account;
import com.github.viqbgrg.springbootoverseer.entity.User;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author bing
 * @since 2020-12-25
 */
public interface IUserService extends IService<User> {

    User findUserByUsername(String username);

    boolean signIn(User users1);

    List<Account> getAllAccount(Long userId);

}
