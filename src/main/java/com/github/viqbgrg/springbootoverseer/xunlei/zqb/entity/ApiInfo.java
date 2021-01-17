package com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity;

import lombok.Data;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
public class ApiInfo {
    private String sessionID;
    private String userID;
    private String nickName;

    private ApiInfo() {

    }

    public ApiInfo(String sessionID, String userID, String nickName) throws UnsupportedEncodingException {
        this.sessionID = sessionID;
        this.userID = userID;
        this.nickName = URLEncoder.encode(nickName, "UTF-8");
        ;
    }
}
