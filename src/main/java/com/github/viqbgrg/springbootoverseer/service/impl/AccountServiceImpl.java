package com.github.viqbgrg.springbootoverseer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.viqbgrg.springbootoverseer.domain.vo.AccountUserInfo;
import com.github.viqbgrg.springbootoverseer.entity.Account;
import com.github.viqbgrg.springbootoverseer.entity.User;
import com.github.viqbgrg.springbootoverseer.entity.UserAccount;
import com.github.viqbgrg.springbootoverseer.mapper.AccountMapper;
import com.github.viqbgrg.springbootoverseer.service.BaseServiceImpl;
import com.github.viqbgrg.springbootoverseer.service.IAccountService;
import com.github.viqbgrg.springbootoverseer.service.IUserAccountService;
import com.github.viqbgrg.springbootoverseer.service.IUserService;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity.AccountInfo;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity.ApiInfo;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity.XunleiAccount;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.exception.WkyCustomErrorException;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.exception.WkyUnknownErrorException;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.exception.WkyUsernamePasswordException;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.service.ZqbApi;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.service.ZqbLogin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author bing
 * @since 2020-12-31
 */
@Service
public class AccountServiceImpl extends BaseServiceImpl<AccountMapper, Account> implements IAccountService {

    private final IUserAccountService userAccountService;

    private final IUserService userService;

    public AccountServiceImpl(IUserAccountService userAccountService, IUserService userService) {
        this.userAccountService = userAccountService;
        this.userService = userService;
    }

    @Override
    public void create(XunleiAccount xunleiAccount) throws WkyUnknownErrorException, IOException, WkyUsernamePasswordException {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        LocalDateTime now = LocalDateTime.now();
        AccountInfo login = ZqbLogin.login(xunleiAccount);
        Account account = new Account();
        BeanUtils.copyProperties(login, account);
        account.setUserName(xunleiAccount.getUsername());
        account.setPassword(xunleiAccount.getPassword());
        account.setUserID(Long.parseLong(login.getUserID()));
        account.setCreateAt(now);
        account.setUpdateAt(now);
        this.save(account);
        UserAccount userAccount = new UserAccount();
        userAccount.setUserID(account.getUserID());
        userAccount.setUserId(user.getId());
        userAccount.setCreateAt(now);
        userAccount.setUpdateAt(now);
        userAccountService.save(userAccount);
    }

    @Override
    public void deleteAccount(String accountId) {
        this.removeByIdMyException(accountId);
    }

    @Override
    public List<Account> getAccountList(List<Long> userIds) {
        List<Account> accounts = this.baseMapper.selectList(new LambdaQueryWrapper<Account>().in(Account::getUserID, userIds));
        return accounts;
    }

    @Override
    public void collectAll(Long userId) {

        List<Account> accounts = userService.getAllAccount(userId);
        accounts.forEach(account -> {
            try {
                ApiInfo apiInfo = new ApiInfo(account.getSessionID(), account.getUserID(), account.getNickName());
                ZqbApi zqbApi = new ZqbApi(apiInfo);
                zqbApi.collect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void drawcashAll(Long userId) {
        List<Account> accounts = userService.getAllAccount(userId);
        accounts.forEach(account -> {
            try {
                ApiInfo apiInfo = new ApiInfo(account.getSessionID(), account.getUserID(), account.getNickName());
                ZqbApi zqbApi = new ZqbApi(apiInfo);
                zqbApi.drawCash();
            } catch (IOException | WkyCustomErrorException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public AccountUserInfo getAccountUserInfo(Long userId) {
        List<Account> accounts = userService.getAllAccount(userId);
        return null;
    }
}
