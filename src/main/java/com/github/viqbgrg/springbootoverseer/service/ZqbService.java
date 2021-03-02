package com.github.viqbgrg.springbootoverseer.service;

import com.github.viqbgrg.springbootoverseer.entity.Account;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity.AccountInfo;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.exception.WkyUnknownErrorException;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.exception.WkyUsernamePasswordException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface ZqbService {

    void updateAccount();

    AccountInfo login(Account account) throws WkyUnknownErrorException, IOException, WkyUsernamePasswordException;

    AccountInfo loginKey(Account account) throws WkyUnknownErrorException, IOException, WkyUsernamePasswordException;

    void getProduceStat(Account account) throws IOException;

    void getUserData(Account account) throws IOException, WkyUnknownErrorException, WkyUsernamePasswordException;

    void saveHistory(Account account);
}
