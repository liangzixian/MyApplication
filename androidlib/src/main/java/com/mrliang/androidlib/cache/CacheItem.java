package com.mrliang.androidlib.cache;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/27 0027.
 */
public class CacheItem implements Serializable{

    /**
     * 存储的key
     */
    private final String key;

    /**
     * JSON字符串
     */
    private String data;

    /**
     * 过期时间的时间戳
     */
    private long timeStamp = 0L;

    public CacheItem(final String key, final String data, final long expiredTime) {
        this.key = key;
        this.timeStamp = System.currentTimeMillis() + expiredTime * 1000;
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String getData() {
        return data;
    }
}
