package com.{company_name}.android.{app_package_name_prefix}.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.{company_name}.android.{app_package_name_prefix}.{app_class_prefix}App;

import butterknife.ButterKnife;
import rx.Observable;
import rx.android.lifecycle.LifecycleEvent;
import rx.subjects.BehaviorSubject;

/**
 * Common functionality for all fragments in the app
 */
public abstract class BaseFragment extends Fragment {

    private final BehaviorSubject<LifecycleEvent> lifecycleSubject = BehaviorSubject.create();

    /**
     * @return id of the layout to use in this fragment
     */
    @LayoutRes
    protected abstract int getLayoutId();

    protected {app_class_prefix}App app;

    public Observable<LifecycleEvent> lifecycle() {
        return lifecycleSubject.asObservable();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = ({app_class_prefix}App) getActivity().getApplicationContext();
        lifecycleSubject.onNext(LifecycleEvent.CREATE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(getLayoutId(), container, false);

        ButterKnife.inject(this, view);
        onCreateView(view, savedInstanceState);

        return view;
    }

    /**
     * @see #onCreateView(LayoutInflater, ViewGroup, Bundle)
     */
    protected void onCreateView(View rootView, Bundle savedInstanceState) {
        // Hook for subclasses to override
    }

    @Override
    public void onDestroyView() {
        ButterKnife.reset(this);
        lifecycleSubject.onNext(LifecycleEvent.DESTROY_VIEW);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        lifecycleSubject.onNext(LifecycleEvent.DESTROY);
        super.onDestroy();
        app.getRefWatcher().watch(this, getClass().getSimpleName());
    }

    @Override
    public void onAttach(android.app.Activity activity) {
        super.onAttach(activity);
        lifecycleSubject.onNext(LifecycleEvent.ATTACH);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lifecycleSubject.onNext(LifecycleEvent.CREATE_VIEW);
    }

    @Override
    public void onStart() {
        super.onStart();
        lifecycleSubject.onNext(LifecycleEvent.START);
    }

    @Override
    public void onResume() {
        super.onResume();
        lifecycleSubject.onNext(LifecycleEvent.RESUME);
    }

    @Override
    public void onPause() {
        lifecycleSubject.onNext(LifecycleEvent.PAUSE);
        super.onPause();
    }

    @Override
    public void onStop() {
        lifecycleSubject.onNext(LifecycleEvent.STOP);
        super.onStop();
    }

    @Override
    public void onDetach() {
        lifecycleSubject.onNext(LifecycleEvent.DETACH);
        super.onDetach();
    }
}