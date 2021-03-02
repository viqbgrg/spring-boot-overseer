package com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "r",
        "rd",
        "is_tm",
        "is_bd",
        "tm_tip",
        "draw_flag"
})
public class DrawcashInfo {

    @JsonProperty("r")
    private int r;
    @JsonProperty("rd")
    private String rd;
    @JsonProperty("is_tm")
    private int isTm;
    @JsonProperty("is_bd")
    private int isBd;
    @JsonProperty("tm_tip")
    private String tmTip;
    @JsonProperty("draw_flag")
    private int drawFlag;

    @JsonProperty("r")
    public int getR() {
        return r;
    }

    @JsonProperty("r")
    public void setR(int r) {
        this.r = r;
    }

    @JsonProperty("rd")
    public String getRd() {
        return rd;
    }

    @JsonProperty("rd")
    public void setRd(String rd) {
        this.rd = rd;
    }

    @JsonProperty("is_tm")
    public int getIsTm() {
        return isTm;
    }

    @JsonProperty("is_tm")
    public void setIsTm(int isTm) {
        this.isTm = isTm;
    }

    @JsonProperty("is_bd")
    public int getIsBd() {
        return isBd;
    }

    @JsonProperty("is_bd")
    public void setIsBd(int isBd) {
        this.isBd = isBd;
    }

    @JsonProperty("tm_tip")
    public String getTmTip() {
        return tmTip;
    }

    @JsonProperty("tm_tip")
    public void setTmTip(String tmTip) {
        this.tmTip = tmTip;
    }

    @JsonProperty("draw_flag")
    public int getDrawFlag() {
        return drawFlag;
    }

    @JsonProperty("draw_flag")
    public void setDrawFlag(int drawFlag) {
        this.drawFlag = drawFlag;
    }

}