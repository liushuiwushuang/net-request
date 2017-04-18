package com.shlg.chuang.littledemo.util;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static void sendOkHttpGetRequest(String address, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        request.method();
        client.newCall(request).enqueue(callback);
    }

    public static void sendOkHttpPostRequest(String address, String bodyJson, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(JSON, bodyJson);
        Request request = new Request.Builder().url(address).post(requestBody).build();
        request.method();
        client.newCall(request).enqueue(callback);
    }
}
