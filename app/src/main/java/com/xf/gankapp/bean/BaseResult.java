package com.xf.gankapp.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by X-FAN on 2016/6/21.
 */
public class BaseResult {
    @SerializedName("error")
    private boolean error;

    public boolean isError() {
        return error;
    }
}
