package com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity;

import lombok.Data;

/**
 * @author viqbgrg
 */
@Data
public class ApiCookies {
    private String sessionid;
    private Long userid;
    private String origin;
    private String nickname;
    private String usernick;
    private String usrname;
    private String v;

    @Override
    public String toString() {
        return "sessionid=" + sessionid +
                "; userid=" + userid +
                "; origin=" + origin +
                "; nickname=" + nickname +
                "; usernick=" + usernick +
                "; usrname=" + usrname +
                "; v=" + v;
    }
}
