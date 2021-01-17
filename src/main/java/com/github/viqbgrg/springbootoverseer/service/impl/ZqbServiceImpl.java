package com.github.viqbgrg.springbootoverseer.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.github.viqbgrg.springbootoverseer.entity.Account;
import com.github.viqbgrg.springbootoverseer.service.IAccountService;
import com.github.viqbgrg.springbootoverseer.service.ZqbService;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.common.JsonUtil;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity.AccountInfo;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity.ApiInfo;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity.LoginKeyDto;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity.XunleiAccount;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.exception.WkyUnknownErrorException;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.exception.WkyUsernamePasswordException;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.service.ZqbApi;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.service.ZqbLogin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author viqbg
 */
@Service
@Slf4j
public class ZqbServiceImpl implements ZqbService {

    private List<Account> accounts;
    private IAccountService accountService;

    public ZqbServiceImpl(IAccountService accountService) {
        this.accountService = accountService;
    }


    @Override
    public void updateAccount() {
        accounts = accountService.list();
    }

    /**
     * 重新登陆,然后重新保存loginKey等
     *
     * @param account
     * @return
     * @throws WkyUnknownErrorException
     * @throws IOException
     * @throws WkyUsernamePasswordException
     */
    @Override
    public AccountInfo login(Account account) throws WkyUnknownErrorException, IOException, WkyUsernamePasswordException {
        XunleiAccount xunleiAccount = new XunleiAccount();
        xunleiAccount.setUsername(account.getUsername());
        xunleiAccount.setPassword(account.getPassword());
        AccountInfo login = ZqbLogin.login(xunleiAccount);
        account.setUpdateAt(LocalDateTime.now());
        account.setCreditkey(login.getCreditkey());
        account.setLoginKey(login.getLoginKey());
        account.setSecureKey(login.getSecureKey());
        accountService.save(account);
        return login;
    }

    @Override
    public AccountInfo loginKey(Account account) throws WkyUnknownErrorException, IOException, WkyUsernamePasswordException {
        LoginKeyDto loginKeyDto = new LoginKeyDto(account.getCreditkey(), account.getUserId(), account.getLoginKey(), ZqbLogin.devicesign(SecureUtil.md5(account.getUsername() + "23333")));
        AccountInfo accountInfo = ZqbLogin.loginKey(loginKeyDto);
        account.setUpdateAt(LocalDateTime.now());
        account.setCreditkey(accountInfo.getCreditkey());
        account.setLoginKey(accountInfo.getLoginKey());
        account.setSecureKey(accountInfo.getSecureKey());
        accountService.save(account);
        return accountInfo;
    }

    @Override
    public void getProduceStat(Account account) throws IOException {
        ApiInfo apiInfo = new ApiInfo(account.getSessionID(), account.getUserId(), account.getNickName());
        ZqbApi zqbApi = new ZqbApi(apiInfo);
        String produceStat = zqbApi.getProduceStat();
    }

    @Override
    public void getUserData(Account account) throws IOException, WkyUnknownErrorException, WkyUsernamePasswordException {
        log.info("获取账号{}的信息", account.getUsername());
        ApiInfo apiInfo = new ApiInfo(account.getSessionID(), account.getUserId(), account.getNickName());
        ZqbApi zqbApi = new ZqbApi(apiInfo);
        String mineInfo = zqbApi.getMineInfo();
        Map<String, Object> mineInfoMap = JsonUtil.stringToObject(mineInfo);
        int r = (Integer) mineInfoMap.get("r");
        if (r != 0) {
            AccountInfo accountInfo = loginKey(account);
        }
    }


}
