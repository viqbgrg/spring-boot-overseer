package com.github.viqbgrg.springbootoverseer.xunlei.service;

import cn.hutool.crypto.SecureUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.viqbgrg.springbootoverseer.xunlei.common.HttpUtil;
import com.github.viqbgrg.springbootoverseer.xunlei.common.JsonUtil;
import com.github.viqbgrg.springbootoverseer.xunlei.entity.*;
import com.github.viqbgrg.springbootoverseer.xunlei.exception.WkyUnknownErrorException;
import com.github.viqbgrg.springbootoverseer.xunlei.exception.WkyUsernamePasswordException;
import lombok.extern.java.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 赚钱宝登陆
 *
 * @author nnj
 */
@Log
public class ZqbLogin {
    private static final String LOGIN_URL = "https://xluser-ssl.xunlei.com/xluser.core.login/v3/login";
    private static final String LOGIN_KEY_URL = "https://xluser-ssl.xunlei.com/xluser.core.login/v3/loginkey";
    private XunleiAccount account;
    private String deviceKey;

    private ZqbLogin() {

    }

    public ZqbLogin(XunleiAccount account) {
        this.account = account;
        deviceKey = devicesign(SecureUtil.md5(account.getUsername() + "23333"));
    }

    private static String devicesign(String paramString) {
        StringBuffer localStringBuffer = new StringBuffer();
        localStringBuffer.append("div101");
        localStringBuffer.append(".");
        localStringBuffer.append(paramString);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(paramString);
        stringBuilder.append("com.xunlei.redcrystalandroid");
        stringBuilder.append(61);
        stringBuilder.append("b241119a03814304aff756279b7dbb87");
        localStringBuffer.append(SecureUtil.md5(SecureUtil.sha1(stringBuilder.toString())));
        return localStringBuffer.toString();
    }

    public LoginResultDto login() throws IOException, WkyUsernamePasswordException, WkyUnknownErrorException {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUserName(account.getUsername());
        loginResponse.setPassWord(account.getPassword());
        loginResponse.setProtocolVersion("301");
        loginResponse.setSequenceNo("1000003");
        loginResponse.setPlatformVersion("10");
        loginResponse.setIsCompressed("0");
        loginResponse.setAppid("61");
        loginResponse.setClientVersion("3.2.2");
        loginResponse.setPeerID("00000000000000000000000000000000");
        loginResponse.setAppName("ANDROID-com.xunlei.redcrystalandroid");
        loginResponse.setSdkVersion("188000");
        loginResponse.setDevicesign(deviceKey);
        loginResponse.setNetWorkType("WIFI");
        loginResponse.setProviderName("OTHER");
        loginResponse.setDeviceModel("ONEPLUS A3010");
        loginResponse.setDeviceName("Oneplus A3010");
        loginResponse.setOSVersion("9");
        loginResponse.setCreditkey("");
        loginResponse.setIsMd5Pwd("0");
        String body = JsonUtil.pojoToString(loginResponse);

        Map<String, String> map = new HashMap<>();
        map.put("User-Agent", "android-ok-http-client/xl-acc-sdk/version-3.6.1.188000");
        map.put("Content-Type", "application/json");


        String content = HttpUtil.post(LOGIN_URL, body, map);
        try {
            return JsonUtil.stringToPOJO(content, LoginResultDto.class);
        } catch (JsonProcessingException e) {
            // 异常: 密码错误等
            log.warning(content);
            LoginErrorDto dto = JsonUtil.stringToPOJO(content, LoginErrorDto.class);
            if ("3".equals(dto.getErrorCode())) {
                throw new WkyUsernamePasswordException(dto.getErrorDesc());
            } else {
                throw new WkyUnknownErrorException(dto.getErrorDesc());
            }

        }


    }

    public LoginResultDto loginkey(LoginResultDto loginInfo) throws IOException, WkyUsernamePasswordException, WkyUnknownErrorException {
        LoginKeyDto dto = new LoginKeyDto();
        dto.setProtocolVersion("301");
        dto.setSequenceNo("1000001");
        dto.setPlatformVersion("10");
        dto.setIsCompressed("0");
        dto.setAppid("61");
        dto.setClientVersion("3.2.2");
        dto.setPeerID("00000000000000000000000000000000");
        dto.setAppName("ANDROID-com.xunlei.redcrystalandroid");
        dto.setSdkVersion("188000");
        dto.setDevicesign(deviceKey);
        dto.setNetWorkType("WIFI");
        dto.setProviderName("OTHER");
        dto.setDeviceModel("ONEPLUS A3010");
        dto.setDeviceName("Oneplus A3010");
        dto.setOSVersion("9");
        dto.setCreditkey(loginInfo.getCreditkey());
        dto.setUserName(loginInfo.getUserID());
        dto.setLoginKey(loginInfo.getLoginKey());

        String body = JsonUtil.pojoToString(dto);


        Map<String, String> map = new HashMap<>(1);
        map.put("User-Agent", "android-ok-http-client/xl-acc-sdk/version-3.6.1.188000");
        map.put("Content-Type", "application/json");

        String content = HttpUtil.post(LOGIN_KEY_URL, body, map);
        try {
            return JsonUtil.stringToPOJO(content, LoginResultDto.class);
        } catch (JsonProcessingException e) {
            // 异常: 密码错误等
            log.warning(content);
            LoginErrorDto loginErrorDto = JsonUtil.stringToPOJO(content, LoginErrorDto.class);
            if ("3".equals(loginErrorDto.getErrorCode())) {
                throw new WkyUsernamePasswordException(loginErrorDto.getErrorDesc());
            } else {
                throw new WkyUnknownErrorException(loginErrorDto.getErrorDesc());
            }

        }
    }
}
