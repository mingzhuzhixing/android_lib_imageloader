package com.v.imageloader;

import android.app.Application;

import com.nj.baijiayun.imageloader.loader.ImageLoader;

/**
 * ClassName: MyApplication
 * Description:
 *
 * @author zhuming
 * @package_name com.v.imageloader
 * @date 2022/6/18 13:09
 */
public class MyApplication extends Application {
    public static Application instace;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化
        ImageLoader.init(this);
        instace=this;
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        ImageLoader.getLoader().trimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        ImageLoader.getLoader().onLowMemory();
    }
}
