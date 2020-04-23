package com.ztf.storage.chaincache;

import com.ztf.storage.callback.ResultCallback;

/**
 * 网络部分不适合封装到Storage基础组件中来
 * @param <T>
 */
@Deprecated
public class NetCacheChain<T> extends StorageChain<T> {
    @Override
    protected void saveData(String key, T data) {

    }

    @Override
    protected void getData(String key, ResultCallback callback) {

    }

    @Override
    protected void removeByKey(String key) {

    }

    @Override
    protected void clearData() {

    }
}
