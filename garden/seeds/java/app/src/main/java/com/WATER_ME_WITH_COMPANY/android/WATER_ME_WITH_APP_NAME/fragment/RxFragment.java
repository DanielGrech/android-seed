package com.{{company_name}}.android.{{app_package_name_prefix}}.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import rx.Observable;
import com.trello.rxlifecycle.FragmentEvent;
import rx.subjects.BehaviorSubject;

/**
 * Fragment which exposes useful lifecycle callbacks as an {@link rx.Observable}
 */
abstract class RxFragment extends Fragment {

    private final BehaviorSubject<FragmentEvent> lifecycleSubject = BehaviorSubject.create();

    public Observable<FragmentEvent> lifecycle() {
        return lifecycleSubject.asObservable();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleSubject.onNext(FragmentEvent.CREATE);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(android.app.Activity activity) {
        super.onAttach(activity);
        lifecycleSubject.onNext(FragmentEvent.ATTACH);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lifecycleSubject.onNext(FragmentEvent.CREATE_VIEW);
    }

    @Override
    public void onStart() {
        super.onStart();
        lifecycleSubject.onNext(FragmentEvent.START);
    }

    @Override
    public void onResume() {
        super.onResume();
        lifecycleSubject.onNext(FragmentEvent.RESUME);
    }

    @Override
    public void onPause() {
        lifecycleSubject.onNext(FragmentEvent.PAUSE);
        super.onPause();
    }

    @Override
    public void onStop() {
        lifecycleSubject.onNext(FragmentEvent.STOP);
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        lifecycleSubject.onNext(FragmentEvent.DESTROY_VIEW);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        lifecycleSubject.onNext(FragmentEvent.DESTROY);
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        lifecycleSubject.onNext(FragmentEvent.DETACH);
        super.onDetach();
    }
}
