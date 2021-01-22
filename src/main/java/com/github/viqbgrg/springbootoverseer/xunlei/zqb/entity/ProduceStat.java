package com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "r",
        "rd",
        "hourly_list",
        "dt"
})
@Data
public class ProduceStat {
    @JsonProperty("r")
    private Integer r;
    @JsonProperty("rd")
    private String rd;
    @JsonProperty("hourly_list")
    private List<Integer> hourlyList;
    @JsonProperty("dt")
    private Integer dt;

}
