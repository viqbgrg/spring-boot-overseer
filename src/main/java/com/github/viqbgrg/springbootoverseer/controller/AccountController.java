package com.github.viqbgrg.springbootoverseer.controller;


import com.github.viqbgrg.springbootoverseer.domain.dto.XunleiAccountDto;
import com.github.viqbgrg.springbootoverseer.domain.vo.AccountUserInfo;
import com.github.viqbgrg.springbootoverseer.entity.User;
import com.github.viqbgrg.springbootoverseer.service.IAccountService;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity.XunleiAccount;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.exception.WkyUnknownErrorException;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.exception.WkyUsernamePasswordException;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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

    private final IAccountService accountService;

    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping
    public ResponseEntity<Void> create(@RequestBody XunleiAccountDto xunleiAccountDto) throws WkyUnknownErrorException, IOException, WkyUsernamePasswordException {
        XunleiAccount xunleiAccount = new XunleiAccount();
        BeanUtils.copyProperties(xunleiAccountDto, xunleiAccount);
        accountService.create(xunleiAccount);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 删除
     */
    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> delete(@PathVariable String accountId) {
        accountService.deleteAccount(accountId);
        return ResponseEntity.ok().build();
    }

    /**
     * collect 收集水晶
     */
    @PostMapping("/collect/all")
    public ResponseEntity<Void> collectAll() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        accountService.collectAll(user.getId());
        return ResponseEntity.ok().build();
    }

    /**
     * collect 提现
     */
    @PostMapping("/drawcash/all")
    public ResponseEntity<Void> drawcashAll() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        accountService.drawcashAll(user.getId());
        return ResponseEntity.ok().build();
    }


    /**
     * collect 提现
     */
    @ApiOperation(value = "获取用户的账号信息")
    @GetMapping("/accountInfo")
    public ResponseEntity<AccountUserInfo> getAccountInfo() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        AccountUserInfo accountUserInfo = accountService.getAccountUserInfo(user.getId());
        return ResponseEntity.ok().body(accountUserInfo);
    }
}
