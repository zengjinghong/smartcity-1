package com.example.demo.utils;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public abstract class ICallback<T> implements Callback {
    @Override
    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
        String res = response.body().string().replace("/prod-api/", Constants.API_URL);
        final T bean = new Gson().fromJson(res, ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        new Handler(Looper.getMainLooper()).post(() -> bix(bean));
        response.close();
    }

    @Override
    public void onFailure(@NonNull Call call, @NonNull IOException e) {
        Log.d("request", "onFailure: " + e.getMessage());
    }

    protected abstract void bix(T bean);
}
