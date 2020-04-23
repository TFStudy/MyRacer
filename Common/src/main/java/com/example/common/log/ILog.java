package com.example.common.log;

public interface ILog {
    void d(String log);
    void d(String TAG,String log);

    void i(String log);
    void i(String TAG,String log);

    void w(String log);
    void w(String TAG,String log);

    void e(String log);
    void e(String TAG,String log);
}
