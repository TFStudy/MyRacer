package com.ztf.storage.common;

import android.content.Context;
import android.os.Environment;

import com.example.common.app.AppUtils;
import com.example.common.encrypt.MD5;
import com.example.common.other.ObjUtils;
import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 内置存储上的LRU缓存
 * @param <T>
 */
public class ZDiskLRUCache<T> {

    private static ZDiskLRUCache instance=new ZDiskLRUCache();

    /**
     * 容量上限 50M
     */
    private static final int MAX_SIZE = 50 * 1024 * 1024;
    private DiskLruCache diskLruCache;
    /**
     * 在内置存储上的缓存子目录
     */
    private final String cacheDirName="ZCacheDir";

    private ZDiskLRUCache(){
        if (diskLruCache == null || diskLruCache.isClosed()) {
            try {
                File cacheDir = getDiskCacheDir(AppUtils.getContext(), cacheDirName);
                if (!cacheDir.exists()) {
                    cacheDir.mkdirs();
                }
                //初始化DiskLruCache
                diskLruCache = DiskLruCache.open(cacheDir, 1, 1, MAX_SIZE);//AppUtils.getAppVersionCode(AppUtils.getAppContext())
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static ZDiskLRUCache getInstance(){
        return instance;
    }

    /**
     * 存入数据
     * @param key
     * @param data
     */
    public void saveData(String key, T data) {

        String mKey= MD5.encrypt(key);
        OutputStream outputStream = null;
        DiskLruCache.Editor edit=null;
        try {
            edit = diskLruCache.edit(mKey);
            if (edit!=null){

                //对象转byte数组
                byte[] bytes= ObjUtils.obj2ByteArray(data);


                outputStream = edit.newOutputStream(0);
                outputStream.write(bytes);
                edit.commit();
                diskLruCache.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (edit!=null){
                try {
                    edit.abort();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        }finally {
            if (outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取数据
     * @param key
     * @return
     */
    public T getData(String key) {
        InputStream is=null;
        try {
            List<Byte> data = new ArrayList<>();
            String mKey = MD5.encrypt(key);
            DiskLruCache.Snapshot snapShot = diskLruCache.get(mKey);
            if (snapShot != null) {
                is = snapShot.getInputStream(0);
                byte[] bytes = new byte[2048];
                int len;
                while ((len = is.read(bytes)) != -1) {
                    for (int i = 0; i < len; i++) {
                        data.add(bytes[i]);
                    }
                }
                bytes = new byte[data.size()];
                for (int i = 0; i < bytes.length; i++) {
                    bytes[i] = data.get(i);
                }
                return ObjUtils.byteArray2Object(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 通过key移除数据
     * @param key
     */
    public void removeByKey(String key) {
        String mKey=MD5.encrypt(key);
        try {
            diskLruCache.remove(mKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 全部清除——清理
     */
    public void clearData() {
        try {
            diskLruCache.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 内置存储  sdcard0  外置存储 sdcard1
     * 获取缓存路径
     * @param context
     * @param uniqueName
     * @return
     */
    File getDiskCacheDir(Context context, String uniqueName) {
        final String cachePath = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ||!Environment.isExternalStorageRemovable()
                ? context.getExternalCacheDir().getPath()
                : context.getCacheDir().getPath();
        return new File(cachePath + File.separator + uniqueName);
    }
}
