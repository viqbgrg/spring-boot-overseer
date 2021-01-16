package com.github.viqbgrg.springbootoverseer.xunlei.zqb.service;


import com.github.viqbgrg.springbootoverseer.config.TestConfig;
import com.github.viqbgrg.springbootoverseer.domain.dto.XunleiAccountDto;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.common.JsonUtil;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity.LoginResultDto;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity.XunleiAccount;
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
import java.util.Map;

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
        ZqbLogin zqbLogin = new ZqbLogin(account);
        LoginResultDto login = zqbLogin.login();
        System.out.println(login);
        zqbApi = new ZqbApi(login);
        zqbApi.collect();
        String asset = zqbApi.asset();
        Map<String, Object> stringObjectMap = JsonUtil.stringToObject(asset);
        System.out.println("可提现金额1111: " + stringObjectMap.get("wc_pkg"));
//        Double wc_pkg = (Double) stringObjectMap.get("wc_pkg");
//        String drawpkg = zqbApi.drawpkg(wc_pkg);
//        System.out.println("提现结果:" + drawpkg);
    }

    @Test
    void getProduceStat() throws IOException {
        String produceStat = zqbApi.getProduceStat();
        System.out.println(produceStat);
    }

    @Test
    void drawcashInfo() {
    }

    @Test
    void asset() {
    }

    @Test
    void drawpkg() {
    }

    @Test
    void mineInfo() {
    }

    @Test
    void privilege() {
    }

    @Test
    void testPrivilege() {
    }

    @Test
    void collect() {
    }
}