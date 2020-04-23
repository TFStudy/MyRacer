package com.example.myracer.app;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.common.app.AppUtils;
import com.example.common.log.ZLog;
import com.ztf.imageloader.ImageLoader;
import com.ztf.imageloader.impl.GlideStrategy;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化

        /**
         * 设置Application的上下文提供给各Moudle使用
         */
        AppUtils.setContext(this);

        /**
         * 初始化ImageLoader的策略为Glide
         */
        ImageLoader.getInstance().initStrategy(new GlideStrategy());
    }

    private boolean isDebug() {
        return true;
    }
}
