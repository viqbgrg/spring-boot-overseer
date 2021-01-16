package com.github.viqbgrg.springbootoverseer.service;

import com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity.LoginResultDto;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity.XunleiAccount;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.exception.WkyUnknownErrorException;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.exception.WkyUsernamePasswordException;

import java.io.IOException;

public interface ZqbService {
    LoginResultDto login(XunleiAccount xunleiAccount) throws WkyUnknownErrorException, IOException, WkyUsernamePasswordException;
}
