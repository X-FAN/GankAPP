package com.xf.gankapp.util;

import android.app.Activity;
import android.os.Handler;

/**
 * Created by TC on 2016/3/8.
 *
 */
public class CommonUtils {


    private CommonUtils() {

    }



    /**
     * 延迟加载
     *
     * @param activity
     * @param handler
     * @param runnable
     */
    public static void lazyLoad(Activity activity, final Handler handler, final Runnable runnable) {
        activity.getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        });
    }


















}
