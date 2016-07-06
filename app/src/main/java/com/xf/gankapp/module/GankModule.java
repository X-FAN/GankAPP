package com.xf.gankapp.module;

import com.xf.gankapp.bean.AllResults;
import com.xf.gankapp.module.interfaceModule.IGankModule;
import com.xf.gankapp.network.GankService;
import com.xf.gankapp.network.RetrofitUtil;

import rx.Observable;

/**
 * Created by X-FAN on 2016/6/21.
 */
public class GankModule implements IGankModule {

    @Override
    public Observable<AllResults> getAll(int count, int page) {
        GankService gankService = RetrofitUtil.getRetrofit().create(GankService.class);
        return gankService.getAll(count, page);
    }

    @Override
    public Observable<AllResults> getAndroid(int count, int page) {
        GankService gankService = RetrofitUtil.getRetrofit().create(GankService.class);
        return gankService.getAndroid(count, page);
    }

    @Override
    public Observable<AllResults> getIOS(int count, int page) {
        GankService gankService = RetrofitUtil.getRetrofit().create(GankService.class);
        return gankService.getIOS(count, page);
    }
}
