package com.mrliang.millionmenus.engine;

import android.app.Application;
import android.content.Context;

import com.mrliang.androidlib.cache.CacheManager;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by Administrator on 2016/5/30 0030.
 */
public class MenusApplication extends Application {
    @Override
    public void onCreate() {

        super.onCreate();
        CacheManager.getInstance().initCacheDir();

        initImageLoader(getApplicationContext());

    }

    public static void initImageLoader(Context context) {
        File cacheDir = StorageUtils.getOwnCacheDirectory(context, "/MillionMenus/imageloader");
        ImageLoaderConfiguration config =
                new ImageLoaderConfiguration.Builder(context)
                        .threadPriority(Thread.NORM_PRIORITY - 2)
                        .memoryCacheExtraOptions(480, 800)
                        .memoryCacheSize(2 * 1024 * 1024)
                        .denyCacheImageMultipleSizesInMemory()
                        .diskCache(new UnlimitedDiskCache(cacheDir))
                        .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                        .tasksProcessingOrder(QueueProcessingType.LIFO)
                        .memoryCache(new WeakMemoryCache()).build();
        ImageLoader.getInstance().init(config);
    }
}
