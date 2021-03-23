package com.github.viqbgrg.springbootoverseer.domain.vo;

import com.github.viqbgrg.springbootoverseer.entity.SpeedStat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class AccountUserInfo {
    private String updatedTime;
    private int mPdc;
    private int lastSpeed;
    private int deploySpeed;
    private int wPdc;
    @ApiModelProperty(value = "本月的收益")
    private int yesterdayMPdc;
    private List<SpeedStat> speedStat;
    @ApiModelProperty(value = "本周的收益")
    private int yesterdayWPdc;
    private int pdc;
    private int boxPdc;
    private int balance;
    private int income;
}
