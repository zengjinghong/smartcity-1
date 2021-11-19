package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.demo.beans.NewsBean;
import com.example.demo.beans.UserInfoBean;
import com.example.demo.utils.Constants;
import com.example.demo.utils.ICallback;
import com.example.demo.utils.OkHttpUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OkHttpUtil.request(Constants.USER_INFO, "GET", null, new ICallback<UserInfoBean>() {
            @Override
            protected void bix(UserInfoBean bean) {
                Log.d("request", "bix: " + bean.getUser().getUserName());
            }
        });

        OkHttpUtil.request("/prod-api/press/press/list", "GET", null, new ICallback<NewsBean>() {
            @Override
            protected void bix(NewsBean bean) {
                Log.d("request", "bix: " + bean.getRows().get(0).getContent());
            }
        });
    }
}