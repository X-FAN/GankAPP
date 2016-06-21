package com.xf.gankapp;

/**
 * Created by X-FAN on 2016/6/21.
 */

public interface BaseView<T> {
    void setPresenter(T presenter);

    void showLoading();

    void hideLoading();

    void showTip(String msg);

}

