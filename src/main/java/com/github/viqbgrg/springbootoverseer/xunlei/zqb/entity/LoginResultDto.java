package com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author viqbgrg
 */
@Data
public class LoginResultDto implements Serializable {

    @JsonProperty("vipList")
    private List<VipListItem> vipList;

    @JsonProperty("loginKey")
    private String loginKey;

    @JsonProperty("nickName")
    private String nickName;

    @JsonProperty("errorCode")
    private String errorCode;

    @JsonProperty("sessionID")
    private String sessionID;

    @JsonProperty("error")
    private String error;

    @JsonProperty("keepAliveMinPeriod")
    private String keepAliveMinPeriod;

    @JsonProperty("userName")
    private String userName;

    @JsonProperty("userID")
    private String userID;

    @JsonProperty("version")
    private String version;

    @JsonProperty("isCompressed")
    private String isCompressed;

    @JsonProperty("keepAlivePeriod")
    private String keepAlivePeriod;

    @JsonProperty("userNewNo")
    private String userNewNo;

    @JsonProperty("isSetPassWord")
    private String isSetPassWord;

    @JsonProperty("creditkey")
    private String creditkey;

    @JsonProperty("secureKey")
    private String secureKey;

    @JsonProperty("sequenceNo")
    private String sequenceNo;

    @JsonProperty("error_description")
    private String errorDescription;

    @JsonProperty("platformVersion")
    private String platformVersion;

    @JsonProperty("protocolVersion")
    private String protocolVersion;

    @JsonProperty("expires_in")
    private int expiresIn;

    @JsonProperty("account")
    private String account;

    @JsonProperty("timestamp")
    private String timestamp;
}