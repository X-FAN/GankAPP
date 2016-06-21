package com.xf.gankapp.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by X-FAN on 2016/6/21.
 */
public class ALLResults extends BaseResult {
    @SerializedName("results")
    List<Gank> gankList;

    public List<Gank> getGankList() {
        return gankList;
    }
}
