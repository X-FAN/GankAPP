package com.xf.gankapp.contract;

import com.xf.gankapp.BasePresenter;
import com.xf.gankapp.BaseView;
import com.xf.gankapp.bean.Gank;

import java.util.List;

/**
 * Created by X-FAN on 2016/6/21.
 */
public interface AllContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取所有的干货
         *
         * @param count
         * @param page
         */
        void subscribeAllGank(int count, int page);
    }

    interface View extends BaseView<Presenter> {
        /**
         * 展示干货
         *
         * @param gankList
         */
        void showAllGank(List<Gank> gankList);
    }
}
