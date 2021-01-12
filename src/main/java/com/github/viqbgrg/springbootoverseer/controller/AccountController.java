package com.github.viqbgrg.springbootoverseer.controller;


import com.github.viqbgrg.springbootoverseer.domain.dto.XunleiAccountDto;
import com.github.viqbgrg.springbootoverseer.entity.Account;
import com.github.viqbgrg.springbootoverseer.entity.UserAccount;
import com.github.viqbgrg.springbootoverseer.service.IAccountService;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity.LoginResultDto;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity.XunleiAccount;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.exception.WkyUnknownErrorException;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.exception.WkyUsernamePasswordException;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.service.ZqbLogin;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bing
 * @since 2020-12-31
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private IAccountService accountService;


    @PostMapping
    public ResponseEntity<Void> create(@RequestBody XunleiAccountDto xunleiAccountDto) throws WkyUnknownErrorException, IOException, WkyUsernamePasswordException {
        XunleiAccount xunleiAccount = new XunleiAccount();
        LocalDateTime now = LocalDateTime.now();
        BeanUtils.copyProperties(xunleiAccountDto, xunleiAccount);
        ZqbLogin zqbLogin = new ZqbLogin(xunleiAccount);
        LoginResultDto login = zqbLogin.login();
        Account account = new Account();
        BeanUtils.copyProperties(login, account);
        account.setCreateAt(now);
        account.setUpdateAt(now);
        accountService.save(account);
        UserAccount userAccount = new UserAccount();
//        userAccount.setAccountId();
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
