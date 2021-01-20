package com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity;

import lombok.Data;

@Data
public class MineInfo {
    private int r;
    private String rd;
    private int td_not_in_a;
    private int td_box_pdc;
    private int b_unget;
    private int b_open;
    private int td_cm_pdc;
    private int cm_num;
    private DevM dev_m;
    private int steal_get;
    private int s_novice;
    private int s;
    private int w_s;
    private int td_s;
    private int td_c;
    private int w_zqb;
    private boolean upgrade;
    private int act_id;
    private String icon;
    @Data
    static class DevM{
        private int pdc;
        private String info;
        private String url;
        private boolean upgrade;
    }

}
