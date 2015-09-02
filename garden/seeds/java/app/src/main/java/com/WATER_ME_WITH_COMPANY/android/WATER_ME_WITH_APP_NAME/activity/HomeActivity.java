package com.{{company_name}}.android.{{app_package_name_prefix}}.activity;

import android.content.Context;
import android.os.Bundle;

import com.{{company_name}}.android.{{app_package_name_prefix}}.R;
import com.{{company_name}}.android.{{app_package_name_prefix}}.mvp.presenter.HomePresenter;
import com.{{company_name}}.android.{{app_package_name_prefix}}.mvp.view.HomeMvpView;
import com.{{company_name}}.android.{{app_package_name_prefix}}.module.AppServicesComponent;

public class HomeActivity extends PresentableActivity<HomePresenter> implements HomeMvpView {

    @Override
    protected HomePresenter createPresenter(AppServicesComponent component) {
        return new HomePresenter(this, component);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.act_home;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Context getContext() {
        return this;
    }

}
