package com.xf.gankapp.contract;

import com.xf.gankapp.BasePresenter;
import com.xf.gankapp.BaseView;
import com.xf.gankapp.bean.Gank;

import java.util.List;

/**
 * Created by X-FAN on 2016/6/21.
 */
public interface IAllContract {
    public interface IPresenter extends BasePresenter {
        void getAllGank(int count, int page);
    }

    public interface IAllView extends BaseView<IPresenter> {
        void showAllGank(List<Gank> gankList);
    }
}
