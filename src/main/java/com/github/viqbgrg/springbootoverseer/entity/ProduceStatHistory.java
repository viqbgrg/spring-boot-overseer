package com.github.viqbgrg.springbootoverseer.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProduceStatHistory implements Serializable {
    private List<Integer> hourlyList;
    private int mid;
}
