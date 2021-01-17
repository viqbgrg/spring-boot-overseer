package com.github.viqbgrg.springbootoverseer.xunlei.zqb.common;

import okhttp3.*;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

/**
 * @author viqbgrg
 */
public class HttpUtil {
    private static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");


    /**
     * peiluyou.com 证书过期,去除验证
     */
    private static OkHttpClient client = getUnsafeOkHttpClient().build();

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

    public static String postForm(String url, RequestBody formBody, Map<String, String> headersMap) throws IOException {

        Headers headers = Headers.of(headersMap);
        Request request = new Request.Builder()
                .url(url)
                .headers(headers)
                .post(formBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }


    private static OkHttpClient.Builder getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain,
                                                       String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain,
                                                       String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            return new OkHttpClient.Builder()
                    .sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0])
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
