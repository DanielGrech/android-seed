package com.{{company_name}}.android.{{app_package_name_prefix}}.mvp.presenter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.{{company_name}}.android.{{app_package_name_prefix}}.activity.BaseActivity;
import com.{{company_name}}.android.{{app_package_name_prefix}}.fragment.BaseFragment;
import com.{{company_name}}.android.{{app_package_name_prefix}}.util.RxUtils;
import com.{{company_name}}.android.{{app_package_name_prefix}}.mvp.view.MvpView;
import com.{{company_name}}.android.{{app_package_name_prefix}}.module.AppServicesComponent;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Base class for all presenters (In the Model-View-Presenter architecture) within the application
 */
public abstract class Presenter<V extends MvpView> {

    private final V view;

    private CompositeSubscription subscriptions;

    public Presenter(@NonNull V view, AppServicesComponent component) {
        this.view = view;
        if (this.view == null) {
            throw new IllegalArgumentException("view != null");
        }
    }

    protected V getView() {
        return view;
    }

    protected Context getContext() {
        return view.getContext();
    }

    public void onCreate(Bundle savedInstanceState) {
        subscriptions = new CompositeSubscription();
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {

    }

    public void onDestroy() {

    }

    public void onStart() {

    }

    public void onResume() {

    }

    public void onPause() {
        subscriptions.unsubscribe();
    }

    public void onStop() {

    }

    protected <R> Subscription bind(Observable<R> observable, Observer<? super R> observer) {
        final Observable<R> boundObservable;

        if (view instanceof BaseFragment) {
            boundObservable = RxUtils.bindFragment((BaseFragment) view, observable);
        } else if (getContext() instanceof BaseActivity) {
            boundObservable = RxUtils.bindActivity((BaseActivity) getContext(), observable);
        } else {
            boundObservable = observable;
        }

        final Subscription subscription
                = boundObservable.subscribeOn(Schedulers.io()).subscribe(observer);

        return subscription;
    }

    protected class SimpleSubscriber<T> extends Subscriber<T> {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(T t) {

        }
    }
}
