package com.example.demo.utils;

import android.content.Context;
import android.content.SharedPreferences;

import android.preference.PreferenceManager;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkHttpUtil {
    private static OkHttpClient sClient;
    private static Context sContext;
    private static String sToken;
    private static String BASE_URL = Constants.BASE_URL;
    private static SharedPreferences sSharedPreferences;

    public static void init(Context context) {
        sContext = context;
        sSharedPreferences = PreferenceManager.getDefaultSharedPreferences(sContext);
        sClient = new OkHttpClient();
    }

    public static void request(String url, String method, String json, Callback callback) {
        RequestBody requestBody = null;
        if (json != null) {
            MediaType mediaType = MediaType.Companion.parse("application/json;charset=utf-8");
            requestBody = RequestBody.Companion.create(json, mediaType);
        }

        Request.Builder requestBuilder = new Request.Builder()
                .url(BASE_URL + url)
                .method(method, requestBody);
        sToken = sSharedPreferences.getString("token",null);
        if (sToken != null) {
            requestBuilder.addHeader("Authorization", "Bearer " + sToken);
        }
        Request request = requestBuilder.build();

        sClient.newCall(request).enqueue(callback);
    }

    public static void get(String url, Callback callback) {
        OkHttpUtil.request(url, "GET", null, callback);
    }

    public static void post(String url, String json,Callback callback) {
        OkHttpUtil.request(url, "POST", json, callback);
    }

    public static void put(String url, String json,Callback callback) {
        OkHttpUtil.request(url, "PUT", json, callback);
    }

    public static void delete(String url, String json,Callback callback) {
        OkHttpUtil.request(url, "DELETE", null, callback);
    }
}

