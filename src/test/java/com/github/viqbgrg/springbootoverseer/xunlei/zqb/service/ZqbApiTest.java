package com.github.viqbgrg.springbootoverseer.xunlei.zqb.service;


import com.github.viqbgrg.springbootoverseer.config.TestConfig;
import com.github.viqbgrg.springbootoverseer.domain.dto.XunleiAccountDto;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity.*;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.exception.WkyUnknownErrorException;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.exception.WkyUsernamePasswordException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@Import(TestConfig.class)
@ActiveProfiles("test")
class ZqbApiTest {
    private ZqbApi zqbApi;

    @Autowired
    private XunleiAccountDto xunleiAccountDto;
    @BeforeAll
    void login() throws WkyUnknownErrorException, IOException, WkyUsernamePasswordException {
        XunleiAccount account = new XunleiAccount();
        BeanUtils.copyProperties(xunleiAccountDto, account);
        AccountInfo login = ZqbLogin.login(account);
        ApiInfo apiInfo = new ApiInfo(login.getSessionID(), Long.parseLong(login.getUserID()), login.getNickName());
        zqbApi = new ZqbApi(apiInfo);
    }

    @Test
    void getProduceStat() throws IOException {
        ProduceStat produceStat = zqbApi.getProduceStat();
        System.out.println(produceStat);
    }

    @Test
    void getBalanceInfo() throws IOException {
        BalanceInfo balanceInfo = zqbApi.getBalanceInfo();
        System.out.println(balanceInfo);
    }

    @Test
    void getMineInfo() throws IOException {
        MineInfo mineInfo = zqbApi.getMineInfo();
        System.out.println(mineInfo);
    }

    @Test
    void getDeviceInfo() throws IOException {
        Devices deviceInfo = zqbApi.getDeviceInfo();
        System.out.println(deviceInfo);
    }

    @Test
    void getPrivilege() throws IOException {
        Privilege privilege = zqbApi.getPrivilege();
        System.out.println(privilege);
    }
}