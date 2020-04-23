package com.ztf.storage.common;

import androidx.collection.LruCache;

public class ZLRUCache<T> {
    /**
     * 单例
     */
    private static ZLRUCache instance=new ZLRUCache();
    private final LruCache<String, T> mLruCache;

    public static ZLRUCache getInstance(){
        return instance;
    }

    /**
     * 或者申请到的内存
     */
    private int mTotalSize= (int) Runtime.getRuntime().totalMemory();

    private ZLRUCache(){
        /**
         * 初始化LruCache  一般大小为申请到的内存的1/8
         */
        mLruCache = new LruCache<String,T>(mTotalSize / 8){
            @Override
            protected int sizeOf(String key, T value) {

                /**
                 * bitmap怎么计算大小
                 * 获取bitmap的宽*高*图片编码字节大小  //ARGB8888 RGB565
                 */
                return super.sizeOf(key, value);
            }
        };
    }

    /**
     * 插入数据
     * @param key
     * @param value
     */
    public void put(String key,T value){
        mLruCache.put(key,value);
    }

    /**
     * 获取数据
     * @param key
     * @return
     */
    public T get(String key){
        return mLruCache.get(key);
    }

    /**
     * 删除数据
     * @param key
     */
    public void remove(String key){
        mLruCache.remove(key);
    }

    /**
     * 清理数据
     */
    public void clear(){
        mLruCache.evictAll();
    }

}
