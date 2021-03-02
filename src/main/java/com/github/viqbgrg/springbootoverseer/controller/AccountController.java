package com.github.viqbgrg.springbootoverseer.controller;


import com.github.viqbgrg.springbootoverseer.domain.dto.XunleiAccountDto;
import com.github.viqbgrg.springbootoverseer.entity.Account;
import com.github.viqbgrg.springbootoverseer.entity.User;
import com.github.viqbgrg.springbootoverseer.service.IAccountService;
import com.github.viqbgrg.springbootoverseer.service.IUserService;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity.XunleiAccount;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.exception.WkyUnknownErrorException;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.exception.WkyUsernamePasswordException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

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
    private IUserService userService;

    public AccountController(IAccountService accountService, IUserService userService) {
        this.accountService = accountService;
        this.userService = userService;
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
        List<Account> allAccount = userService.getAllAccount(user.getId());
        accountService.collectAll(allAccount);
        return ResponseEntity.ok().build();
    }
    /**
     * collect 提现
     */
    @PostMapping("/drawcash/all")
    public ResponseEntity<Void> drawcashAll() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        List<Account> allAccount = userService.getAllAccount(user.getId());
        accountService.drawcashAll(allAccount);
        return ResponseEntity.ok().build();
    }
}
