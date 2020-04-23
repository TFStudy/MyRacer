package com.ztf.usercenter.model.storagechain;

import com.example.common.log.ZLog;
import com.ztf.storage.callback.ResultCallback;
import com.ztf.storage.chaincache.StorageChain;

public class RegisterStorageChain<T> extends StorageChain<T> {
    @Override
    protected void saveData(String key, T data) {
        ZLog.getInstance().d("RegisterStorageChain save");
    }

    @Override
    protected void getData(String key, ResultCallback callback) {
        ZLog.getInstance().d("RegisterStorageChain get");
    }

    @Override
    protected void removeByKey(String key) {

    }

    @Override
    protected void clearData() {

    }
}
