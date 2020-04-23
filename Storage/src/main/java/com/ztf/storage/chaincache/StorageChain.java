package com.ztf.storage.chaincache;

import com.ztf.storage.callback.ResultCallback;

/**
 * 存储链的基类
 * @param <T>
 */
public abstract class StorageChain<T> {

    /**
     * 下一个节点
     */
    protected StorageChain nextChain;
    /**
     * 上一个节点
     */
    protected StorageChain previousChain;

    /**
     * 设置下游链以及将下游链的上一节点指定为当前节点
     * @param _storageChain
     */
    public void setNextChain(StorageChain _storageChain){
        nextChain=_storageChain;
        _storageChain.previousChain=this;
    }

    /**
     * 存储数据
     * @param key
     * @param data
     */
    public void save(String key,T data){
        saveData(key,data);
        if (this.nextChain!=null){
            this.nextChain.save(key,data);
        }
    }

    /**
     * 获取数据
     * @param key
     * @param result
     */
    public void get(final String key, final ResultCallback<T> result){
        getData(key,new ResultCallback<T>(){

            @Override
            public void getData(T data) {
                if (null==data&&nextChain!=null){
                    nextChain.get(key,result);
                }else{
                    /**
                     * 同步给上一存储链节点
                     */
                    if (previousChain!=null){
                        previousChain.save(key,data);
                    }
                    result.getData(data);
                }
            }
        });
    }

    /**
     * 按key删除数据
     * @param key
     */
    public void removeAtKey(String key){
        removeByKey(key);
        if (nextChain!=null){
            nextChain.removeAtKey(key);
        }
    }

    /**
     * 清理数据
     */
    public void clear(){
        clearData();
        if (nextChain!=null){
            nextChain.clear();
        }
    }

    /**
     * 存储数据
     * @param key
     * @param data
     */
    protected abstract void saveData(String key, T data);

    /**
     * 获取数据
     * @param key
     * @param callback
     */
    protected abstract void getData(String key,ResultCallback callback);

    /**
     * 按key删除指定数据
     * @param key
     */
    protected abstract void removeByKey(String key);

    /**
     * 清理数据
     */
    protected abstract void clearData();

}
