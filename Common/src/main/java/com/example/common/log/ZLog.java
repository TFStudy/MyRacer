package com.example.common.log;

import com.example.common.BuildConfig;

public class ZLog {
    private static ZLog instance=new ZLog();
    private ZLog(){
        mLog=new LogcatImpl();
    }
    public static ZLog getInstance(){
        return instance;
    }

    private ILog mLog;
    /**
     * 是否是Debug模式
     */
    private boolean isDebug= BuildConfig.IsDebug;

    /**
     * 通过外界设置log策略
     * @param _log
     */
    public void setLogType(ILog _log){
        mLog=_log;
    }

    /**
     * 通过外界设置Debug的模式
     * @param _isDebug
     */
    public void openDebug(boolean _isDebug){
        isDebug=_isDebug;
    }

    public void d(String log){
        if (isDebug){
            mLog.d(log);
        }
    }

    public void d(String TAG,String log){
        if (isDebug){
            mLog.d(TAG,log);
        }
    }

    public void i(String log){
        if (isDebug){
            mLog.i(log);
        }
    }

    public void i(String TAG,String log){
        if (isDebug){
            mLog.i(TAG,log);
        }
    }

    public void w(String log){
        if (isDebug){
            mLog.w(log);
        }
    }

    public void w(String TAG,String log){
        if (isDebug){
            mLog.w(TAG,log);
        }
    }

    public void e(String log){
        if (isDebug){
            mLog.e(log);
        }
    }

    public void e(String TAG,String log){
        if (isDebug){
            mLog.e(TAG,log);
        }
    }
}
