package com.ztf.storage.callback;

/**
 * 异步回调
 * @param <T>
 */
public interface ResultCallback<T> {
    void getData(T data);
}
