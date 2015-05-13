package com.{company_name}.android.{app_package_name_prefix}.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

abstract class BaseFragment extends Fragment {

    /**
     * @return id of the layout to use in this fragment
     */
    protected abstract @LayoutRes int getLayoutId();

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(getLayoutId(), container, false);

        ButterKnife.inject(this, view);
        onCreateView(view, savedInstanceState);

        return view;
    }

    protected void onCreateView(View rootView, Bundle savedInstanceState) {
        // Hook for subclasses to override
    }

    @Override
    public void onDestroyView() {
        ButterKnife.reset(this);
        super.onDestroyView();
    }
}