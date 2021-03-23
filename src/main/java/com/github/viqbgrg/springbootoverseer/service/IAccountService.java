package com.github.viqbgrg.springbootoverseer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.viqbgrg.springbootoverseer.domain.vo.AccountUserInfo;
import com.github.viqbgrg.springbootoverseer.entity.Account;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity.XunleiAccount;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.exception.WkyUnknownErrorException;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.exception.WkyUsernamePasswordException;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author bing
 * @since 2020-12-31
 */
public interface IAccountService extends IService<Account> {
    public void create(XunleiAccount xunleiAccount) throws WkyUnknownErrorException, IOException, WkyUsernamePasswordException;

    void deleteAccount(String accountId);

    List<Account> getAccountList(List<Long> userIds);

    void collectAll(Long userId);

    void drawcashAll(Long userId);

    AccountUserInfo getAccountUserInfo(Long id);
}
