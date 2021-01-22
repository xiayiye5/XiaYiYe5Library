package cn.xiayiye5.xiayiye5library.utils;

import android.graphics.Bitmap;

import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * TODO: 图片内存缓存
 *
 * @author soyoungboy
 * @version 1.0.0
 * @date 2014-6-25 下午2:57:04
 */
public class MemoryCache {

    public static final String TAG = MemoryCache.class.getSimpleName();

    private final HashMap<String, SoftReference<Bitmap>> cache = new HashMap<>();


    /**
     * 从缓存中取出图片
     */
    public Bitmap get(String id) {
        if (!cache.containsKey(id)) {
            return null;
        }
        SoftReference<Bitmap> ref = cache.get(id);
        return ref.get();
    }


    /**
     * 将图片存入软引用
     */
    public void put(String id, Bitmap bitmap) {
        cache.put(id, new SoftReference<>(bitmap));
    }


    public void clear() {
        cache.clear();
    }
}