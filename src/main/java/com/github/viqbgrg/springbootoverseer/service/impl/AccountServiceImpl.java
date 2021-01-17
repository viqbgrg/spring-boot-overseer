package com.github.viqbgrg.springbootoverseer.service.impl;

import com.github.viqbgrg.springbootoverseer.entity.Account;
import com.github.viqbgrg.springbootoverseer.entity.User;
import com.github.viqbgrg.springbootoverseer.entity.UserAccount;
import com.github.viqbgrg.springbootoverseer.mapper.AccountMapper;
import com.github.viqbgrg.springbootoverseer.service.BaseService;
import com.github.viqbgrg.springbootoverseer.service.IAccountService;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity.AccountInfo;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity.XunleiAccount;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.exception.WkyUnknownErrorException;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.exception.WkyUsernamePasswordException;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.service.ZqbLogin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author bing
 * @since 2020-12-31
 */
@Service
public class AccountServiceImpl extends BaseService<AccountMapper, Account> implements IAccountService {

    @Override
    public void create(XunleiAccount xunleiAccount) throws WkyUnknownErrorException, IOException, WkyUsernamePasswordException {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipals();
        LocalDateTime now = LocalDateTime.now();
        ZqbLogin zqbLogin = new ZqbLogin();
        AccountInfo login = zqbLogin.login(xunleiAccount);
        Account account = new Account();
        BeanUtils.copyProperties(login, account);
        account.setCreateAt(now);
        account.setUpdateAt(now);
        this.save(account);
        UserAccount userAccount = new UserAccount();
        userAccount.setAccountId(account.getId());
        userAccount.setUserId(user.getId());
    }

    @Override
    public void deleteAccount(String accountId) {
        this.removeByIdMyException(accountId);
    }
}
