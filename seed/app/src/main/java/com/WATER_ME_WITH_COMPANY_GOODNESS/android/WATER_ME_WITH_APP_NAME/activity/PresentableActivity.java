package com.{company_name}.android.{app_package_name_prefix}.activity;

import android.os.Bundle;

import com.{company_name}.android.{app_package_name_prefix}.mvp.presenter.Presenter;

abstract class PresentableActivity<T extends Presenter> extends BaseActivity {

    private T presenter;

    protected abstract T createPresenter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.presenter = createPresenter();
        if (this.presenter == null) {
            throw new IllegalStateException("presenter == null");
        }
        presenter.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}