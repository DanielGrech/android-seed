package com.{{company_name}}.android.{{app_package_name_prefix}}.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.{{company_name}}.android.{{app_package_name_prefix}}.{{app_class_prefix}}App;

import butterknife.ButterKnife;

public abstract class BaseActivity extends RxActivity {

    protected {{app_class_prefix}}App app;

    /**
     * @return the layout resource to use for this activity,
     * or a value <= 0 if no layout should be used
     */
    @LayoutRes protected abstract int getLayoutResource();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = ({{app_class_prefix}}App) getApplicationContext();


        final int layoutResId = getLayoutResource();
        if (layoutResId > 0) {
            setContentView(layoutResId);
        }
        ButterKnife.bind(this);
    }

    /**
     * Retrieve a fragment from {@linkplain #getSupportFragmentManager()}
     *
     * @param id  The layout id of the fragment to find
     * @param cls The class of the fragment
     * @param <T>
     * @return A fragment found in the layout with the given id, or null
     */
    @SuppressWarnings("unchecked")
    @Nullable
    protected <T extends Fragment> T findFragment(@IdRes int id, Class<T> cls) {
        return (T) getSupportFragmentManager().findFragmentById(id);
    }
}
