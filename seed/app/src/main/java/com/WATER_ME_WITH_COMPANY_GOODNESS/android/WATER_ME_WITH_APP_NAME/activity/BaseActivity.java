package com.{company_name}.android.{app_package_name_prefix}.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

abstract class BaseActivity extends AppCompatActivity {

    @LayoutRes protected abstract int getLayoutResource();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final int layoutResId = getLayoutResource();
        if (layoutResId > 0) {
            setContentView(layoutResId);
        }
        ButterKnife.inject(this);
    }
}
