package com.xf.gankapp.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by X-FAN on 2016/6/21.
 */
public class Gank {
    @SerializedName("desc")
    private String desc;
    @SerializedName("publishedAt")
    private String publishedAt;
    @SerializedName("type")
    private String type;
    @SerializedName("who")
    private String who;
    @SerializedName("url")
    private String url;

    public String getDesc() {
        return desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getType() {
        return type;
    }

    public String getWho() {
        return who;
    }

    public String getUrl() {
        return url;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
}
