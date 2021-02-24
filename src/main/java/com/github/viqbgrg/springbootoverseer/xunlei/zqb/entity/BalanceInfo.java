package com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

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
@Data
public class BalanceInfo {

    @JsonProperty("r")
    private Integer r;
    @JsonProperty("rd")
    private String rd;
    @JsonProperty("r_h_a")
    private Integer rHA;
    @JsonProperty("r_can_use")
    private Integer rCanUse;
    @JsonProperty("td_not_in_a")
    private Integer tdNotInA;
    @JsonProperty("wc_pkg")
    private Double wcPkg;
    @JsonProperty("gft")
    private Integer gft;
    @JsonProperty("e")
    private String e;
    @JsonProperty("show_e")
    private Integer showE;

}