package com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginErrorDto {

    @JsonProperty("creditkey")
    private String creditkey;

    @JsonProperty("sequenceNo")
    private String sequenceNo;

    @JsonProperty("errorDesc")
    private String errorDesc;

    @JsonProperty("error_description")
    private String errorDescription;

    @JsonProperty("platformVersion")
    private String platformVersion;

    @JsonProperty("errorCode")
    private String errorCode;

    @JsonProperty("protocolVersion")
    private String protocolVersion;

    @JsonProperty("error")
    private String error;

    @JsonProperty("errorIsRetry")
    private int errorIsRetry;

    @JsonProperty("userID")
    private String userID;

    @JsonProperty("errorDescUrl")
    private String errorDescUrl;

    @JsonProperty("isCompressed")
    private String isCompressed;
}