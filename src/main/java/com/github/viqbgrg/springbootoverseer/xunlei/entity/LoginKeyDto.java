package com.github.viqbgrg.springbootoverseer.xunlei.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginKeyDto {

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