package com.github.viqbgrg.springbootoverseer.service.impl;

import com.github.viqbgrg.springbootoverseer.service.ZqbService;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity.LoginResultDto;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity.XunleiAccount;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.exception.WkyUnknownErrorException;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.exception.WkyUsernamePasswordException;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.service.ZqbLogin;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author viqbg
 */
@Service
public class ZqbServiceImpl implements ZqbService {

    @Override
    public LoginResultDto login(XunleiAccount account) throws WkyUnknownErrorException, IOException, WkyUsernamePasswordException {
        return new ZqbLogin(account).login();
    }
}
