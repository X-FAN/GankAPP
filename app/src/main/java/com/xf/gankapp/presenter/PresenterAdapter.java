package com.xf.gankapp.presenter;

import com.xf.gankapp.BasePresenter;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by X-FAN on 2016/9/5.
 */
public class PresenterAdapter implements BasePresenter {
    protected CompositeSubscription mSubscriptions = new CompositeSubscription();

    @Override
    public void unSubscribe() {
        mSubscriptions.clear();
    }

    protected void addSubscription(Subscription subscription) {
        mSubscriptions.add(subscription);
    }
}
