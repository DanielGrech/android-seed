package com.{{company_name}}.android.{{app_package_name_prefix}}.mvp.presenter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.{{company_name}}.android.{{app_package_name_prefix}}.activity.BaseActivity;
import com.{{company_name}}.android.{{app_package_name_prefix}}.fragment.BaseFragment;
import com.{{company_name}}.android.{{app_package_name_prefix}}.util.RxUtils;
import com.{{company_name}}.android.{{app_package_name_prefix}}.mvp.view.HomeMvpView;
import com.{{company_name}}.android.{{app_package_name_prefix}}.module.AppServicesComponent;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class HomePresenter extends Presenter<HomeMvpView> {

    public HomePresenter(@NonNull HomeMvpView view, AppServicesComponent component) {
        super(view, component);
        component.inject(this);
    }
}
