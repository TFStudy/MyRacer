package com.example.common.app;

import android.content.Context;

public class AppUtils {
    private static Context context;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        AppUtils.context = context;
    }
}
