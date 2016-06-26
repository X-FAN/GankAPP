package com.xf.gankapp.module.interfaceModule;

import com.xf.gankapp.bean.AllResults;

import rx.Observable;

/**
 * Created by X-FAN on 2016/6/21.
 */
public interface IGankModule {

    /**
     * 获取全部干货
     * @param count
     * @param page
     * @return
     */
    Observable<AllResults> getAll(int count, int page);
}
