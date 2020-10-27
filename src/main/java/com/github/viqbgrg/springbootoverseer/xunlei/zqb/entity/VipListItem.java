package com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class VipListItem implements Serializable {

    @JsonProperty("vasType")
    private String vasType;

    @JsonProperty("vipGrow")
    private String vipGrow;

    @JsonProperty("isYear")
    private String isYear;

    @JsonProperty("isVip")
    private String isVip;

    @JsonProperty("vasid")
    private String vasid;

    @JsonProperty("vipLevel")
    private String vipLevel;

    @JsonProperty("isAutoDeduct")
    private String isAutoDeduct;

    @JsonProperty("isRemind")
    private String isRemind;

    @JsonProperty("expireDate")
    private String expireDate;

    @JsonProperty("vipDayGrow")
    private String vipDayGrow;

    @JsonProperty("payId")
    private String payId;

    @JsonProperty("payName")
    private String payName;

    @JsonProperty("register")
    private String register;
}