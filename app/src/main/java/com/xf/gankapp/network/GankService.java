package com.xf.gankapp.network;

import com.xf.gankapp.bean.Gank;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by X-FAN on 2016/6/21.
 */
public interface GankService {

    @GET("data/all/{count}/{page}")
    Observable<List<Gank>> getAll(@Path("count") int count, @Path("page") int page);

}
