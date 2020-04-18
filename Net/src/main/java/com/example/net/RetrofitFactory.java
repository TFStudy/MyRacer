package com.example.net;

import android.graphics.Bitmap;

import com.example.net.common.Config;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private static volatile RetrofitFactory instance = null;
    private Retrofit retrofit;
    private OkHttpClient.Builder builder;
    private RetrofitFactory(){
        initRetrofit();
    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Config.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(createOkhttpClient())
                .build();
    }

    private OkHttpClient createOkhttpClient() {
        return getBuilder()
                .addNetworkInterceptor(createLogInterceptor())
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build();
    }

    private Interceptor createLogInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    private OkHttpClient.Builder getBuilder() {
        if(builder == null){
            builder = new OkHttpClient.Builder();
        }
        return builder;
    }
    public static RetrofitFactory getInstance(){
        if(instance == null){
            synchronized (RetrofitFactory.class){
                if(instance == null){
                    instance = new RetrofitFactory();
                }
            }
        }
        return instance;
    }

    public <T> T create(Class<T> service){
        return retrofit.create(service);
    }
}
