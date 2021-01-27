package com.github.viqbgrg.springbootoverseer.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.github.viqbgrg.springbootoverseer.entity.Account;
import com.github.viqbgrg.springbootoverseer.entity.AccountData;
import com.github.viqbgrg.springbootoverseer.service.IAccountDataService;
import com.github.viqbgrg.springbootoverseer.service.IAccountHistoryService;
import com.github.viqbgrg.springbootoverseer.service.IAccountService;
import com.github.viqbgrg.springbootoverseer.service.ZqbService;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity.*;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.exception.WkyUnknownErrorException;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.exception.WkyUsernamePasswordException;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.service.ZqbApi;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.service.ZqbLogin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author viqbg
 */
@Service
@Slf4j
public class ZqbServiceImpl implements ZqbService {

    private List<Account> accounts;
    private IAccountService accountService;
    private IAccountDataService accountDataService;
    private IAccountHistoryService accountHistoryService;


    public ZqbServiceImpl(IAccountService accountService, IAccountDataService accountDataService, IAccountHistoryService accountHistoryService) {
        this.accountService = accountService;
        this.accountDataService = accountDataService;
        this.accountHistoryService = accountHistoryService;
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
        xunleiAccount.setUsername(account.getUserName());
        xunleiAccount.setPassword(account.getPassword());
        AccountInfo login = ZqbLogin.login(xunleiAccount);
        account.setUpdateAt(LocalDateTime.now());
        account.setCreditkey(login.getCreditkey());
        account.setLoginKey(login.getLoginKey());
        account.setSecureKey(login.getSecureKey());
        account.setSessionID(login.getSessionID());
        accountService.updateById(account);
        return login;
    }

    @Override
    public AccountInfo loginKey(Account account) throws WkyUnknownErrorException, IOException, WkyUsernamePasswordException {
        LoginKeyDto loginKeyDto = new LoginKeyDto(account.getCreditkey(), account.getUserID(), account.getLoginKey(), ZqbLogin.devicesign(SecureUtil.md5(account.getUserName() + "23333")));
        AccountInfo accountInfo = ZqbLogin.loginKey(loginKeyDto);
        account.setUpdateAt(LocalDateTime.now());
        account.setSessionID(accountInfo.getSessionID());
        account.setLoginKey(accountInfo.getLoginKey());
        account.setSecureKey(accountInfo.getSecureKey());
        accountService.updateById(account);
        return accountInfo;
    }

    @Override
    public void getProduceStat(Account account) throws IOException {
        ApiInfo apiInfo = new ApiInfo(account.getSessionID(), account.getUserID(), account.getNickName());
        ZqbApi zqbApi = new ZqbApi(apiInfo);
        ProduceStat produceStat = zqbApi.getProduceStat();
    }

    @Override
    public void getUserData(Account account) throws IOException, WkyUnknownErrorException, WkyUsernamePasswordException {
        log.info("获取账号{}的信息", account.getUserID());
        AccountData accountData = new AccountData();
        accountData.setUserID(account.getUserID());
        ApiInfo apiInfo = new ApiInfo(account.getSessionID(), account.getUserID(), account.getNickName());
        ZqbApi zqbApi = new ZqbApi(apiInfo);
        MineInfo mineInfo = zqbApi.getMineInfo();
        accountData.setMineInfo(mineInfo);
        Devices deviceInfo = zqbApi.getDeviceInfo();
        accountData.setDeviceInfo(deviceInfo);
        BalanceInfo balanceInfo = zqbApi.getBalanceInfo();
        accountData.setBalanceInfo(balanceInfo);
        ProduceStat produceStat = zqbApi.getProduceStat();
        accountData.setProduceStat(produceStat);
        accountData.setUpdateAt(LocalDateTime.now());
        accountDataService.saveByException(accountData);
    }

    public void saveHistory(Account account) {
        Long userID = account.getUserID();
        LocalDate now = LocalDate.now();
//        accountHistoryService.getOne(Wrappers.lambdaQuery(AccountHistory.class).allEq());

    }


}
