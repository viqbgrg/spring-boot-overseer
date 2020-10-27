package com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity;

import lombok.Data;

/**
 * @author nnj
 */
@Data
public class LoginResponse {
    private String peerID;
    private String passWord;
    private String appName;
    private String netWorkType;
    private String clientVersion;
    private String userName;
    private String deviceName;
    private String isCompressed;
    private String devicesign;
    private String isMd5Pwd;
    private String creditkey;
    private String sequenceNo;
    private String platformVersion;
    private String appid;
    private String oSVersion;
    private String protocolVersion;
    private String sdkVersion;
    private String deviceModel;
    private String providerName;
}
