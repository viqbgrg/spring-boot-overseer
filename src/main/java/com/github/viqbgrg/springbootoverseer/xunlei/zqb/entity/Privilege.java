package com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "r",
        "rd",
        "mid",
        "phone",
        "level",
        "chk_phone"
})
public class Privilege {

    @JsonProperty("r")
    private Integer r;
    @JsonProperty("rd")
    private String rd;
    @JsonProperty("mid")
    private Integer mid;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("level")
    private Integer level;
    @JsonProperty("chk_phone")
    private Integer chkPhone;
}