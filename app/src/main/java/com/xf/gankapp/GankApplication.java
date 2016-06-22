package com.xf.gankapp;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.xf.gankapp.network.RetrofitUtil;

/**
 * Created by X-FAN on 2016/6/22.
 */
public class GankApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initStetho();
        RetrofitUtil.getInstance();

    }

    private void initStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initialize(Stetho.newInitializerBuilder(this)
                    .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                    .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                    .build());
        }
    }
}
