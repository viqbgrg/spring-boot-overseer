package com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class UbusCdDTO {
    private String jsonrpc;
    private Integer id;
    private String method;
    private List<Object> params = new ArrayList<>();

    public UbusCdDTO(String jsonrpc, Integer id, String method, String sessionId, String server, String serverName, Map params) {
        this(sessionId, server, serverName, params);
        this.jsonrpc = jsonrpc;
        this.id = id;
        this.method = method;
    }

    public UbusCdDTO(String sessionId, String server, String serverName, Map params) {
        this.jsonrpc = "2.0";
        this.id = 1;
        this.method = "call";
        this.params.add(sessionId);
        this.params.add(server);
        this.params.add(serverName);
        this.params.add(params);
    }
}
