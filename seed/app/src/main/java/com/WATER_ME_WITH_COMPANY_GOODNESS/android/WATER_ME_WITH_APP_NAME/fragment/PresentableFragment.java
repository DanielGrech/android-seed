package com.{company_name}.android.{app_package_name_prefix}.fragment;

import android.os.Bundle;

import com.{company_name}.android.{app_package_name_prefix}.mvp.presenter.Presenter;

abstract class PresentableFragment<T extends Presenter> extends BaseFragment {

    protected abstract T getPresenter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresenter().onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getPresenter().onSaveInstanceState(outState);
    }

    @Override
    public void onStart() {
        super.onStart();
        getPresenter().onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().onResume();
    }

    @Override
    public void onPause() {
        getPresenter().onPause();
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        getPresenter().onStop();
    }

    @Override
    public void onDestroy() {
        getPresenter().onDestroy();
        super.onDestroy();
    }
}