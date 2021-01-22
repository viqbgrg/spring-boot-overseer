package com.github.viqbgrg.springbootoverseer.xunlei.zqb.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.common.HttpUtil;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.common.JsonUtil;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.entity.*;
import com.github.viqbgrg.springbootoverseer.xunlei.zqb.exception.WkyExceedTimeException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.RequestBody;

import java.io.IOException;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

/**
 * @author viqbgrg
 */
@Slf4j
public class ZqbApi {
    private static final String URL = "http://1-api-red.xunlei.com";
    private static final String APP_VERSION = "3.2.2";

    private String cookies;
    private ApiInfo apiInfo;

    public ZqbApi(ApiInfo loginInfo) {
        this.apiInfo = loginInfo;
        ApiCookies apiCookies = new ApiCookies();
        apiCookies.setSessionid(loginInfo.getSessionID());
        apiCookies.setUserid(loginInfo.getUserID());
        apiCookies.setOrigin("1");
        String nickName = loginInfo.getNickName();
        apiCookies.setNickname(nickName);
        apiCookies.setUsernick(nickName);
        apiCookies.setUsrname(nickName);
        apiCookies.setV("3.2.2");
        this.cookies = apiCookies.toString();
    }

    public ProduceStat getProduceStat() throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("appversion", APP_VERSION)
                .build();
        String result = HttpUtil.apiPost(URL + "/index.php?r=mine/produce_stat", formBody, cookies);
        ProduceStat produceStat = parsePojo(result, ProduceStat.class);
        return produceStat;
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
    public BalanceInfo getBalanceInfo() throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("v", "2")
                .add("appversion", APP_VERSION)
                .build();
        String result = HttpUtil.apiPost(URL + "/index.php?r=usr/asset", formBody, cookies);
        BalanceInfo balanceInfo = parsePojo(result, BalanceInfo.class);
        return balanceInfo;
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

    private static String getUbusCd(String sessionId, String accountId, UbusCdDTO ubusCdDTO) throws IOException {
        String time = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli() + "";
        //把代理设置到请求配置
        String url = MessageFormat.format("https://ocapi.peiluyou.com:8009/ubus_cd?account_id={0}&session_id={1}&action=ubus_{2}",
                accountId, sessionId, time);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("jsonrpc", "2.0");
        objectNode.put("id", 1);
        objectNode.put("method", "call");
        objectNode.put("params", ubusCdDTO.toString());
        String data = objectMapper.writeValueAsString(ubusCdDTO);


        RequestBody formBody = new FormBody.Builder()
                .addEncoded("data", data)
                .build();
        //实例化CloseableHttpClient对象
        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("Content-Type", "application/x-www-form-urlencoded");
        headersMap.put("Origin", "https://kj.xunlei.com");
        headersMap.put("User-Agent", "Mozilla/5.0 (Linux; Android 10; Redmi K30 Pro Build/QKQ1.191117.002; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/87.0.4280.141 Mobile Safari/537.36");
        String result = HttpUtil.postForm(url, formBody, headersMap);
        String jsonResult = result.substring(result.indexOf("{"), result.lastIndexOf("}") + 1);
        return jsonResult;
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

    /**
     * 获取MINE信息
     *
     * @return
     * @throws IOException
     */
    public MineInfo getMineInfo() throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("v", "4")
                .add("appversion", APP_VERSION)
                .build();
        String result = HttpUtil.apiPost(URL + "/index.php?r=mine/info", formBody, cookies);
        MineInfo mineInfo = parsePojo(result, MineInfo.class);
        return mineInfo;
    }

    public DeviceInfo getDeviceInfo() throws IOException {
        HashMap<String, String> map = new HashMap<>();
        UbusCdDTO dt = new UbusCdDTO(apiInfo.getSessionID(), "server", "get_devices", map);
        String result = ZqbApi.getUbusCd(apiInfo.getSessionID(), apiInfo.getUserID(), dt);
        DeviceInfo deviceInfo = parsePojo(result, DeviceInfo.class);
        return deviceInfo;
    }


    private <T> T parsePojo(String result, Class<T> classType) throws WkyExceedTimeException {
        T t = null;
        try {
            t = JsonUtil.stringToPOJO(result, classType);
        } catch (JsonProcessingException e) {
            log.error("接口报错, 转换异常: {}: {}",classType.getName(), result);
            e.printStackTrace();
            throw new WkyExceedTimeException();
        }
        return t;
    }


}
