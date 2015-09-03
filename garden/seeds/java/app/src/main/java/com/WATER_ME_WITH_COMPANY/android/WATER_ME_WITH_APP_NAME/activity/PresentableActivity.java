package com.{{company_name}}.android.{{app_package_name_prefix}}.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.{{company_name}}.android.{{app_package_name_prefix}}.module.AppServicesComponent;
import com.{{company_name}}.android.{{app_package_name_prefix}}.mvp.presenter.Presenter;

/**
 * Base class for all activities which have a corresponding {@link Presenter} object
 *
 * @param <T> The type of presenter the activity uses
 */
abstract class PresentableActivity<T extends Presenter> extends BaseActivity {

    T presenter;

    /**
     * Return a presenter to use for this activity. This will only be called once per activity,
     * so there is no need to cache the results
     *
     * @param component Used for injecting dependencies
     * @return A {@link Presenter} instance to use with this activity
     */
    @NonNull
    protected abstract T createPresenter(AppServicesComponent component);

    /**
     * @return The presenter instance originally returned from {@link #createPresenter(AppServicesComponent)}
     */
    @NonNull
    protected T getPresenter() {
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.presenter = createPresenter(app.getAppServicesComponent());
        if (this.presenter == null) {
            throw new IllegalStateException("presenter == null");
        }

        presenter.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        presenter = null;
        super.onDestroy();
    }
}