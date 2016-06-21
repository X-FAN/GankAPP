package com.xf.gankapp.module.interfaceModule;

import com.xf.gankapp.bean.Gank;

import java.util.List;

import rx.Observable;

/**
 * Created by X-FAN on 2016/6/21.
 */
public interface IGankModule {

    Observable<List<Gank>> getAll(int count, int page);
}
