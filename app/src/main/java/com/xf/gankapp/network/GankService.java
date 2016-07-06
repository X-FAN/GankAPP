package com.xf.gankapp.network;

import com.xf.gankapp.bean.AllResults;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by X-FAN on 2016/6/21.
 */
public interface GankService {

    @GET("data/all/{count}/{page}")
    Observable<AllResults> getAll(@Path("count") int count, @Path("page") int page);

    @GET("data/Android/{count}/{page}")
    Observable<AllResults> getAndroid(@Path("count") int count, @Path("page") int page);

    @GET("data/iOS/{count}/{page}")
    Observable<AllResults> getIOS(@Path("count") int count, @Path("page") int page);

}
