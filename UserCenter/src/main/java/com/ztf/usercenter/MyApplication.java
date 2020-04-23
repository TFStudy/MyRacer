package com.ztf.usercenter;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.common.app.AppUtils;
import com.ztf.imageloader.ImageLoader;
import com.ztf.imageloader.impl.GlideStrategy;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        registerLifecycle();

        /**
         * 设置Application的上下文提供给各Moudle使用
         */
        AppUtils.setContext(this);

        /**
         * 初始化ImageLoader的策略为Glide
         */
        ImageLoader.getInstance().initStrategy(new GlideStrategy());
//        ZLog.getInstance().setLogType(new ConsoleImpl());
    }

    private void registerLifecycle() {
        this.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
                Log.d("123", "onActivityCreated: ........");
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
                Log.d("123", "onActivityStarted: ......");
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {

            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {

            }
        });
    }

}