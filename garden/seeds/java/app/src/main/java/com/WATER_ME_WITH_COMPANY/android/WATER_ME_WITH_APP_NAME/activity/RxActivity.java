package com.{{company_name}}.android.{{app_package_name_prefix}}.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.trello.rxlifecycle.ActivityEvent;

import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Activity which exposes useful lifecycle callbacks as an {@link Observable}
 */
abstract class RxActivity extends AppCompatActivity {

    private final BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();

    public Observable<ActivityEvent> lifecycle() {
        return lifecycleSubject.asObservable();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleSubject.onNext(ActivityEvent.CREATE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        lifecycleSubject.onNext(ActivityEvent.START);
    }

    @Override
    protected void onResume() {
        super.onResume();
        lifecycleSubject.onNext(ActivityEvent.RESUME);
    }

    @Override
    protected void onPause() {
        lifecycleSubject.onNext(ActivityEvent.PAUSE);
        super.onPause();
    }

    @Override
    protected void onStop() {
        lifecycleSubject.onNext(ActivityEvent.STOP);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        lifecycleSubject.onNext(ActivityEvent.DESTROY);
        super.onDestroy();
    }
}
