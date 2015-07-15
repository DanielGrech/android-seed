package com.{company_name}.android.{app_package_name_prefix}.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.{company_name}.android.{app_package_name_prefix}.{app_class_prefix}App;

import butterknife.ButterKnife;
import rx.Observable;
import rx.android.lifecycle.LifecycleEvent;
import rx.subjects.BehaviorSubject;

public abstract class BaseActivity extends AppCompatActivity {

    private final BehaviorSubject<LifecycleEvent> lifecycleSubject = BehaviorSubject.create();

    protected {app_class_prefix}App app;

    /**
     * @return the layout resource to use for this activity,
     * or a value <= 0 if no layout should be used
     */
    @LayoutRes protected abstract int getLayoutResource();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = ({app_class_prefix}App) getApplicationContext();

        lifecycleSubject.onNext(LifecycleEvent.CREATE);

        final int layoutResId = getLayoutResource();
        if (layoutResId > 0) {
            setContentView(layoutResId);
        }
        ButterKnife.inject(this);
    }

    public Observable<LifecycleEvent> lifecycle() {
        return lifecycleSubject.asObservable();
    }

    @Override
    protected void onStart() {
        super.onStart();
        lifecycleSubject.onNext(LifecycleEvent.START);
    }

    @Override
    protected void onResume() {
        super.onResume();
        lifecycleSubject.onNext(LifecycleEvent.RESUME);
    }

    @Override
    protected void onPause() {
        lifecycleSubject.onNext(LifecycleEvent.PAUSE);
        super.onPause();
    }

    @Override
    protected void onStop() {
        lifecycleSubject.onNext(LifecycleEvent.STOP);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        lifecycleSubject.onNext(LifecycleEvent.DESTROY);
        super.onDestroy();
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
