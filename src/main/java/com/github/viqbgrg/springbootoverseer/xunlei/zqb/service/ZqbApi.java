package com.github.viqbgrg.springbootoverseer.xunlei.zqb.service;


import com.github.viqbgrg.springbootoverseer.xunlei.zqb.common.HttpUtil;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity.ApiCookies;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity.LoginResultDto;
import okhttp3.FormBody;
import okhttp3.RequestBody;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author viqbgrg
 */
public class ZqbApi {
    private static final String URL = "http://1-api-red.xunlei.com";
    private static final String APP_VERSION = "3.2.2";

    private String cookies;

    public ZqbApi(LoginResultDto loginInfo) throws UnsupportedEncodingException {
        ApiCookies apiCookies = new ApiCookies();
        apiCookies.setSessionid(loginInfo.getSessionID());
        apiCookies.setUserid(loginInfo.getUserID());
        apiCookies.setOrigin("1");
        String nickName = URLEncoder.encode(loginInfo.getNickName(), "UTF-8");
        apiCookies.setNickname(nickName);
        apiCookies.setUsernick(nickName);
        apiCookies.setUsrname(nickName);
        apiCookies.setV("3.2.2");
        this.cookies = apiCookies.toString();
    }

    public String getProduceStat() throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("appversion", APP_VERSION)
                .build();
        String result = HttpUtil.apiPost(URL + "/index.php?r=mine/produce_stat", formBody, cookies);
        return result;
    }

    public String drawcashInfo() throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("v", "1")
                .add("appversion", APP_VERSION)
                .build();
        String result = HttpUtil.apiPost(URL + "/index.php?r=usr/drawcashInfo", formBody, cookies);
        return result;
    }

    /**
     * 检测提现余额
     *
     * @return
     * @throws IOException
     */
    public String asset() throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("v", "2")
                .add("appversion", APP_VERSION)
                .build();
        String result = HttpUtil.apiPost(URL + "/index.php?r=usr/asset", formBody, cookies);
        return result;
    }

    /**
     * 提交提现请求
     *
     * @return
     * @throws IOException
     */
    public String drawpkg(double money) throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("v", "3")
                .add("m", String.valueOf(money))
                .build();
        String result = HttpUtil.apiPost(URL + "/index.php?r=usr/drawpkg", formBody, cookies);
        return result;
    }

    /**
     * 获取MINE信息
     *
     * @return
     * @throws IOException
     */
    public String mineInfo(double money) throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("v", "5")
                .add("appversion", APP_VERSION)
                .build();
        String result = HttpUtil.apiPost(URL + "/index.php?r=mine/info", formBody, cookies);
        return result;
    }

    /**
     * 获取个人信息
     *
     * @return
     * @throws IOException
     */
    public String privilege() throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("v", "1")
                .add("appversion", APP_VERSION)
                .add("ver", APP_VERSION)
                .build();
        String result = HttpUtil.apiPost(URL + "/index.php?r=usr/privilege", formBody, cookies);
        return result;
    }

    /**
     * 获取设备状态
     *
     * @return
     * @throws IOException
     */
    public String privilege(String type) throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("v", "3")
                .add("appversion", APP_VERSION)
                .add("type", type)
                .build();
        String result = HttpUtil.apiPost(URL + "/index.php?r=mine/devices_stat", formBody, cookies);
        return result;
    }

    /**
     * 提交收集水晶请求
     *
     * @return
     * @throws IOException
     */
    public String collect() throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("v", "4")
                .add("appversion", APP_VERSION)
                // 暂时不知道有什么用
//                .add("_skey", sKey)
                .add("cmid", "-1")
                .build();
        String result = HttpUtil.apiPost(URL + "/index.php?r=mine/collect", formBody, cookies);
        return result;
    }


}
