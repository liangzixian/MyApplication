package com.mrliang.menus.engine;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;
import com.mrliang.androidlib.cache.CacheManager;

/**
 * Created by Administrator on 2016/5/30 0030.
 */
public class MenusApplication extends Application {
    @Override
    public void onCreate() {

        super.onCreate();
        CacheManager.getInstance().initCacheDir();

        AVOSCloud.initialize(this,"i2kNDijaO3BNSlbNRz1oWQo0-gzGzoHsz","FcWzqaqDJzxvv9WHBTmVMdQ0");
        AVOSCloud.useAVCloudCN();
//        initImageLoader(getApplicationContext());

    }

//    public static void initImageLoader(Context context) {
//        File cacheDir = StorageUtils.getOwnCacheDirectory(context, "/MillionMenus/imageloader");
//        ImageLoaderConfiguration config =
//                new ImageLoaderConfiguration.Builder(context)
//                        .threadPriority(Thread.NORM_PRIORITY - 2)
//                        .memoryCacheExtraOptions(480, 800)
//                        .memoryCacheSize(2 * 1024 * 1024)
//                        .denyCacheImageMultipleSizesInMemory()
//                        .diskCache(new UnlimitedDiskCache(cacheDir))
//                        .diskCacheFileNameGenerator(new Md5FileNameGenerator())
//                        .tasksProcessingOrder(QueueProcessingType.LIFO)
//                        .memoryCache(new WeakMemoryCache()).build();
//        ImageLoader.getInstance().init(config);
//    }
}
