package com.xf.gankapp.presenter;

import com.xf.gankapp.bean.AllResults;
import com.xf.gankapp.bean.Gank;
import com.xf.gankapp.contract.AllContract;
import com.xf.gankapp.module.GankModule;
import com.xf.gankapp.module.interfaceModule.IGankModule;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by X-FAN on 2016/6/22.
 */
public class AllPresenter implements AllContract.Presenter {
    private CompositeSubscription mSubscriptions;
    private IGankModule mGankModule;
    private AllContract.View mAllView;

    public AllPresenter(AllContract.View view) {
        mSubscriptions = new CompositeSubscription();
        mGankModule = new GankModule();
        mAllView = view;
        mAllView.setPresenter(this);
    }

    @Override
    public void subscribeAllGank(int count, int page) {
        Subscription subscription = mGankModule.getAndroid(count, page)
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
                .flatMap(new Func1<List<Gank>, Observable<Gank>>() {
                    @Override
                    public Observable<Gank> call(List<Gank> ganks) {
                        return Observable.from(ganks);
                    }
                }).map(new Func1<Gank, Gank>() {
                    @Override
                    public Gank call(Gank gank) {
                        String date = gank.getPublishedAt().substring(0, 10);
                        gank.setPublishedAt(date);
                        return gank;
                    }
                }).toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Gank>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mAllView.showTip(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Gank> ganks) {
                        mAllView.showAllGank(ganks);
                    }
                });
        mSubscriptions.add(subscription);
    }


    @Override
    public void unSubscribe() {
        mSubscriptions.clear();

    }
}
