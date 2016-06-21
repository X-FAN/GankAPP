package com.xf.gankapp.module;

import com.xf.gankapp.bean.Gank;
import com.xf.gankapp.module.interfaceModule.IGankModule;
import com.xf.gankapp.network.GankService;
import com.xf.gankapp.network.RetrofitUtil;

import java.util.List;

import rx.Observable;

/**
 * Created by X-FAN on 2016/6/21.
 */
public class GankModule implements IGankModule {

    @Override
    public Observable<List<Gank>> getAll(int count, int page) {
        GankService gankService = RetrofitUtil.getRetrofit().create(GankService.class);
        gankService.getAll(count, page);
        return gankService.getAll(count, page);
    }
}
