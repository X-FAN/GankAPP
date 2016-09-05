package com.xf.gankapp.presenter;

import com.xf.gankapp.bean.AllResults;
import com.xf.gankapp.bean.Gank;
import com.xf.gankapp.contract.AndroidContract;
import com.xf.gankapp.module.GankModule;
import com.xf.gankapp.module.interfaceModule.IGankModule;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by X-FAN on 2016/7/6.
 */
public class AndroidPresenter extends PresenterAdapter implements AndroidContract.Presenter {

    private IGankModule mGankModule;
    private AndroidContract.View mAndroidView;


    public AndroidPresenter(AndroidContract.View view) {
        mGankModule = new GankModule();
        mAndroidView = view;
        mAndroidView.setPresenter(this);
    }

    @Override
    public void subscribeAndroidGank(int count, int page) {
        Subscription subscription = mGankModule.getAndroid(count, page)
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<AllResults, Observable<List<Gank>>>() {
                    @Override
                    public Observable<List<Gank>> call(final AllResults allResults) {
                        if (!allResults.isError()) {
                            return Observable.create(new Observable.OnSubscribe<List<Gank>>() {
                                @Override
                                public void call(Subscriber<? super List<Gank>> subscriber) {
                                    subscriber.onNext(allResults.getGankList());
                                    subscriber.onCompleted();
                                }
                            });
                        }
                        return Observable.error(new Exception("数据返回异常"));
                    }
                })
                .flatMap(new Func1<List<Gank>, Observable<Gank>>() {
                    @Override
                    public Observable<Gank> call(List<Gank> ganks) {
                        return Observable.from(ganks);
                    }
                })
                .map(new Func1<Gank, Gank>() {
                    @Override
                    public Gank call(Gank gank) {
                        String date = gank.getPublishedAt().substring(0, 10);
                        gank.setPublishedAt(date);
                        return gank;
                    }
                })
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Gank>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mAndroidView.showTip(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Gank> ganks) {
                        mAndroidView.showAndroidGank(ganks);
                    }
                });
        addSubscription(subscription);
    }
}
