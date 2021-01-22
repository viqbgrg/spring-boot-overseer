package com.github.viqbgrg.springbootoverseer.service.impl;

import com.github.viqbgrg.springbootoverseer.config.TestConfig;
import com.github.viqbgrg.springbootoverseer.domain.dto.XunleiAccountDto;
import com.github.viqbgrg.springbootoverseer.entity.Account;
import com.github.viqbgrg.springbootoverseer.service.IAccountService;
import com.github.viqbgrg.springbootoverseer.service.ZqbService;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity.AccountInfo;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity.XunleiAccount;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.exception.WkyUnknownErrorException;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.exception.WkyUsernamePasswordException;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.service.ZqbLogin;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

//@Import({ZqbServiceImpl.class})
@ActiveProfiles("test")
@SpringBootTest
@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@ContextConfiguration(classes = {TestConfig.class, ZqbServiceImpl.class, AccountDataServiceImpl.class}, initializers = ConfigDataApplicationContextInitializer.class)
//@ExtendWith({MockitoExtension.class, SpringExtension.class})
class ZqbServiceImplTest {
    @Autowired
    private XunleiAccountDto xunleiAccountDto;

    private static Account account;
    @Autowired
    private ZqbService zqbService;
    @MockBean
    private IAccountService accountService;
    @BeforeAll
    public void setup() throws WkyUnknownErrorException, IOException, WkyUsernamePasswordException {
        XunleiAccount xunleiAccount = new XunleiAccount();
        BeanUtils.copyProperties(xunleiAccountDto, xunleiAccount);
        ZqbLogin zqbLogin = new ZqbLogin();
        AccountInfo login = zqbLogin.login(xunleiAccount);
        account = new Account();
        BeanUtils.copyProperties(login, account);
        log.info(account.toString());
    }

    @Test
    void login() {
        assertThat(xunleiAccountDto.getUsername()).isNotNull();
    }

    @Test
    void getUserData() throws WkyUsernamePasswordException, IOException, WkyUnknownErrorException {
        zqbService.getUserData(account);
    }
}