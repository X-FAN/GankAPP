package com.xf.gankapp.contract;

import com.xf.gankapp.BasePresenter;
import com.xf.gankapp.BaseView;
import com.xf.gankapp.bean.Gank;

import java.util.List;

/**
 * Created by X-FAN on 2016/7/6.
 */
public interface AndroidContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取所有的android干货
         *
         * @param count
         * @param page
         */
        void subscribeAndroidGank(int count, int page);
    }

    interface View extends BaseView<Presenter> {
        /**
         * 展示android干货
         *
         * @param gankList
         */
        void showAndroidGank(List<Gank> gankList);
    }
}
