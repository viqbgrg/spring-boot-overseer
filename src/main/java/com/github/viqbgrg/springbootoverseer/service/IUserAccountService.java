package com.github.viqbgrg.springbootoverseer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.viqbgrg.springbootoverseer.entity.Account;
import com.github.viqbgrg.springbootoverseer.entity.UserAccount;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bing
 * @since 2020-12-31
 */
public interface IUserAccountService extends IService<UserAccount> {
    List<UserAccount> getAllAccount(Long userId);
}
