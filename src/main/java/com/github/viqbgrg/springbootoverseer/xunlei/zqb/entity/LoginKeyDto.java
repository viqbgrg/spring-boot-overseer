package com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginKeyDto {

    public LoginKeyDto(String creditkey, Long userId, String loginKey, String deviceKey) {
        this.setProtocolVersion("301");
        this.setSequenceNo("1000001");
        this.setPlatformVersion("10");
        this.setIsCompressed("0");
        this.setAppid("61");
        this.setClientVersion("3.2.2");
        this.setPeerID("00000000000000000000000000000000");
        this.setAppName("ANDROID-com.xunlei.redcrystalandroid");
        this.setSdkVersion("188000");
        this.setDevicesign(deviceKey);
        this.setNetWorkType("WIFI");
        this.setProviderName("OTHER");
        this.setDeviceModel("ONEPLUS A3010");
        this.setDeviceName("Oneplus A3010");
        this.setOSVersion("9");
        this.setCreditkey(creditkey);
        this.setUserName(Long.toString(userId));
        this.setLoginKey(loginKey);
    }

    @JsonProperty("peerID")
    private String peerID;

    @JsonProperty("appName")
    private String appName;

    @JsonProperty("netWorkType")
    private String netWorkType;

    @JsonProperty("loginKey")
    private String loginKey;

    @JsonProperty("clientVersion")
    private String clientVersion;

    @JsonProperty("userName")
    private String userName;

    @JsonProperty("deviceName")
    private String deviceName;

    @JsonProperty("isCompressed")
    private String isCompressed;

    @JsonProperty("devicesign")
    private String devicesign;

    @JsonProperty("creditkey")
    private String creditkey;

    @JsonProperty("sequenceNo")
    private String sequenceNo;

    @JsonProperty("platformVersion")
    private String platformVersion;

    @JsonProperty("appid")
    private String appid;

    @JsonProperty("OSVersion")
    private String oSVersion;

    @JsonProperty("protocolVersion")
    private String protocolVersion;

    @JsonProperty("sdkVersion")
    private String sdkVersion;

    @JsonProperty("deviceModel")
    private String deviceModel;

    @JsonProperty("providerName")
    private String providerName;
}