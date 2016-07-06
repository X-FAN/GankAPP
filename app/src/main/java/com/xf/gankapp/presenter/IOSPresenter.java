package com.xf.gankapp.presenter;

import com.xf.gankapp.bean.AllResults;
import com.xf.gankapp.bean.Gank;
import com.xf.gankapp.contract.IOSContract;
import com.xf.gankapp.module.GankModule;
import com.xf.gankapp.module.interfaceModule.IGankModule;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by X-FAN on 2016/7/6.
 */
public class IOSPresenter implements IOSContract.Presenter {

    private CompositeSubscription mSubscriptions;
    private IGankModule mGankModule;
    private IOSContract.View mIOSView;


    public IOSPresenter(IOSContract.View view) {
        mSubscriptions = new CompositeSubscription();
        mGankModule = new GankModule();
        mIOSView = view;
        mIOSView.setPresenter(this);
    }
    @Override
    public void subscribeIOSGank(int count, int page) {
        Subscription subscription = mGankModule.getIOS(count, page)
                .subscribeOn(Schedulers.io())
                .map(new Func1<AllResults, List<Gank>>() {

                    @Override
                    public List<Gank> call(AllResults allResults) {
                        if (!allResults.isError()) {
                            return allResults.getGankList();
                        }
                        return null;
                    }
                })
                .map(new Func1<List<Gank>, List<Gank>>() {
                    @Override
                    public List<Gank> call(List<Gank> ganks) {
                        if (ganks != null) {
                            for (Gank gank : ganks) {//处理日期
                                String date = gank.getPublishedAt().substring(0, 10);
                                gank.setPublishedAt(date);
                            }
                        }
                        return ganks;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Gank>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mIOSView.showTip(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Gank> ganks) {
                        mIOSView.showIOSGank(ganks);
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void unSubscribe() {

    }
}
