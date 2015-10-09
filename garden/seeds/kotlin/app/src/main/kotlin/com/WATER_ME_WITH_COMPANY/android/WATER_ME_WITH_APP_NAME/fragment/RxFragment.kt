package com.{{company_name}}.android.{{app_package_name_prefix}}.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.trello.rxlifecycle.FragmentEvent
import rx.Observable
import rx.subjects.BehaviorSubject

/**
 * Fragment which exposes useful lifecycle callbacks as an [rx.Observable]
 */
abstract class RxFragment : Fragment() {

    private val lifecycleSubject = BehaviorSubject.create<FragmentEvent>()

    public fun lifecycle(): Observable<FragmentEvent> {
        return lifecycleSubject.asObservable()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleSubject.onNext(FragmentEvent.CREATE)
    }

    @Suppress("DEPRECATED_SYMBOL_WITH_MESSAGE")
    override fun onAttach(activity: android.app.Activity?) {
        super.onAttach(activity)
        lifecycleSubject.onNext(FragmentEvent.ATTACH)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleSubject.onNext(FragmentEvent.CREATE_VIEW)
    }

    override fun onStart() {
        super.onStart()
        lifecycleSubject.onNext(FragmentEvent.START)
    }

    override fun onResume() {
        super.onResume()
        lifecycleSubject.onNext(FragmentEvent.RESUME)
    }

    override fun onPause() {
        lifecycleSubject.onNext(FragmentEvent.PAUSE)
        super.onPause()
    }

    override fun onStop() {
        lifecycleSubject.onNext(FragmentEvent.STOP)
        super.onStop()
    }

    override fun onDestroyView() {
        lifecycleSubject.onNext(FragmentEvent.DESTROY_VIEW)
        super.onDestroyView()
    }

    override fun onDestroy() {
        lifecycleSubject.onNext(FragmentEvent.DESTROY)
        super.onDestroy()
    }

    override fun onDetach() {
        lifecycleSubject.onNext(FragmentEvent.DETACH)
        super.onDetach()
    }
}
