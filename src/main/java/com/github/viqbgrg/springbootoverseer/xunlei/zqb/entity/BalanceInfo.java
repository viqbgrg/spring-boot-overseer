package com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "r",
        "rd",
        "r_h_a",
        "r_can_use",
        "td_not_in_a",
        "wc_pkg",
        "gft",
        "e",
        "show_e"
})
public class BalanceInfo {

    @JsonProperty("r")
    public Integer r;
    @JsonProperty("rd")
    public String rd;
    @JsonProperty("r_h_a")
    public Integer rHA;
    @JsonProperty("r_can_use")
    public Integer rCanUse;
    @JsonProperty("td_not_in_a")
    public Integer tdNotInA;
    @JsonProperty("wc_pkg")
    public Double wcPkg;
    @JsonProperty("gft")
    public Integer gft;
    @JsonProperty("e")
    public String e;
    @JsonProperty("show_e")
    public Integer showE;

}