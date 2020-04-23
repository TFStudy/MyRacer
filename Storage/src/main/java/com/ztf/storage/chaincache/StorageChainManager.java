package com.ztf.storage.chaincache;

import com.ztf.storage.callback.ResultCallback;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 存储管理类
 */
public class StorageChainManager {

    /**
     * 内存存储链
     */
    private MemoryCacheChain memoryCacheChain=null;
    /**
     * 内置存储/磁盘存储链
     */
    private DiskCacheChain diskCacheChain=null;

    /**
     * 缓存各资源存储链
     */
    private static ConcurrentHashMap<String,StorageChain> chainMap;


    private static volatile StorageChainManager instance=null;
    private StorageChainManager(){
        chainMap=new ConcurrentHashMap<>();
    }

    /**
     * 单例
     * @return
     */
    public static StorageChainManager getInstance(){
        if (null==instance){
            synchronized (StorageChainManager.class){
                if (null==instance){
                    instance=new StorageChainManager();
                }
            }
        }
        return instance;
    }

    /**
     * 存入数据
     * @param key
     * @param data
     * @param <T>
     */
    public <T> void saveData(String key,T data){
        if (!chainMap.containsKey(key)){
            initChain(key);
        }

        chainMap.get(key).save(key,data);
    }

    /**
     * 初始化链结构
     * @param key
     * @return
     */
    private StorageChain initChain(String key) {
        memoryCacheChain=new MemoryCacheChain();
        diskCacheChain=new DiskCacheChain();
        //将内存存储关联都内存存储链上
        memoryCacheChain.setNextChain(diskCacheChain);

        chainMap.put(key,memoryCacheChain);
        return diskCacheChain;
    }

    /**
     * 获取数据
     * @param key
     * @param callback
     */
    public <T> void getData(String key, ResultCallback<T> callback){
        if (chainMap.containsKey(key)){
            chainMap.get(key).get(key,callback);
        }
        else{
            throw new RuntimeException("current storage chain is not found");
        }
    }

    /**
     * 根据key删除数据
     * @param key
     */
    public void removeAtKey(String key){
        if (chainMap.containsKey(key)){
            chainMap.get(key).removeAtKey(key);
        }
        else{
            throw new RuntimeException("current storage chain is not found");
        }
    }

    /**
     * 清理数据
     */
    public void clear(){
        if (chainMap.size()>0){
            for (Map.Entry<String,StorageChain> entry:
                    chainMap.entrySet()) {
                entry.getValue().clear();
            }
        }
    }

    /**
     * 外部添加存储链节点
     * @param key
     * @param storageChain
     */
    public void addChain(String key,StorageChain storageChain){
        if (chainMap.containsKey(key)){
            StorageChain storageChain1 = chainMap.get(key);
            while (storageChain1!=null){
                storageChain1=storageChain1.nextChain;
            }
            storageChain1.nextChain=storageChain;
        }
        else{
            StorageChain lastStorageChain = initChain(key);
            lastStorageChain.setNextChain(storageChain);
        }
    }

}
