package com.github.viqbgrg.springbootoverseer.xunlei.zqb.common;

import okhttp3.*;

import java.io.IOException;
import java.util.Map;

/**
 * @author viqbgrg
 */
public class HttpUtil {
    private static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    private static OkHttpClient client = new OkHttpClient();

    public static String post(String url, String json, Map<String, String> headersMap) throws IOException {
        Headers headers = Headers.of(headersMap);
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .headers(headers)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static String apiPost(String url, RequestBody body, String cookies) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Cookie", cookies)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
