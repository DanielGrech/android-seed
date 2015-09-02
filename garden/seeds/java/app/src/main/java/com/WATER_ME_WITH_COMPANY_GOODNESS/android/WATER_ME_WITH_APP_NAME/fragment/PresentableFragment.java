package com.{company_name}.android.{app_package_name_prefix}.fragment;

import android.os.Bundle;

import com.{company_name}.android.{app_package_name_prefix}.module.AppServicesComponent;
import com.{company_name}.android.{app_package_name_prefix}.mvp.presenter.Presenter;

/**
 * Base class for all fragments which have a corresponding {@link Presenter} object
 *
 * @param <T> The type of presenter the fragment uses
 */
public abstract class PresentableFragment<T extends Presenter> extends BaseFragment {

    private T presenter;

    protected abstract T createPresenter(AppServicesComponent servicesComponent);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter(app.getAppServicesComponent());
        if (presenter == null) {
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
    public void onStart() {
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
    public void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        presenter = null;
        super.onDestroy();
    }

    public final T getPresenter() {
        return presenter;
    }
}