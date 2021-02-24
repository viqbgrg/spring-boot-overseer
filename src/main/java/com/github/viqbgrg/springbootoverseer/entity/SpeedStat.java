package com.github.viqbgrg.springbootoverseer.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SpeedStat implements Serializable {
    private int[] devSpeed;
    private Integer mid;
}
