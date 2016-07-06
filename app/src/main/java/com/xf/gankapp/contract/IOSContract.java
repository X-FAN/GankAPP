package com.xf.gankapp.contract;

import com.xf.gankapp.BasePresenter;
import com.xf.gankapp.BaseView;
import com.xf.gankapp.bean.Gank;

import java.util.List;

/**
 * Created by X-FAN on 2016/7/6.
 */
public class IOSContract {
    public interface Presenter extends BasePresenter {
        /**
         * 获取所有的ios干货
         *
         * @param count
         * @param page
         */
        void subscribeIOSGank(int count, int page);
    }

    public interface View extends BaseView<Presenter> {
        /**
         * 展示ios干货
         *
         * @param gankList
         */
        void showIOSGank(List<Gank> gankList);
    }
}
