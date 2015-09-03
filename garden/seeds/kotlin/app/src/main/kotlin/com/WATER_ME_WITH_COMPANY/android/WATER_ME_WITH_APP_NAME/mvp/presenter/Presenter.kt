package com.{{company_name}}.android.{{app_package_name_prefix}}.mvp.presenter

import android.content.Context
import android.os.Bundle
import com.{{company_name}}.android.{{app_package_name_prefix}}.activity.BaseActivity
import com.{{company_name}}.android.{{app_package_name_prefix}}.fragment.BaseFragment
import com.{{company_name}}.android.{{app_package_name_prefix}}.module.AppServicesComponent
import com.{{company_name}}.android.{{app_package_name_prefix}}.mvp.view.MvpView
import com.{{company_name}}.android.{{app_package_name_prefix}}.util.bind
import com.{{company_name}}.android.{{app_package_name_prefix}}.util.observeOnMainThread
import com.{{company_name}}.android.{{app_package_name_prefix}}.util.subscribeOnIoThread
import rx.Observable
import rx.Observer
import rx.Subscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import javax.inject.Inject

/**
 * Base class for all presenters (In the Model-View-Presenter architecture) within the application
 */
public abstract class Presenter<V : MvpView>(private val view: V, private val component : AppServicesComponent) {

    private var subscriptions: CompositeSubscription? = null

    protected fun getContext(): Context {
        return view.getContext()
    }

    protected fun getView() : V {
        return view
    }

    public open fun onCreate(savedInstanceState: Bundle?) {
        subscriptions = CompositeSubscription()
    }

    public open fun onSaveInstanceState(savedInstanceState: Bundle?) {
    }

    public open fun onDestroy() {
    }

    public open fun onStart() {
    }

    public open fun onResume() {
    }

    public open fun onPause() {
        subscriptions!!.unsubscribe()
    }

    public open fun onStop() {
    }

    protected fun <T> bind(observable: Observable<T>, observer: Observer<in T>): Subscription {
        val boundObservable: Observable<T>


        val cxt = getContext()
        if (cxt is BaseActivity) {
            boundObservable = observable.bind(cxt)
        } else if (cxt is BaseFragment) {
            boundObservable = observable.bind(cxt)
        } else {
            boundObservable = observable
        }

        return boundObservable.observeOnMainThread()
                .subscribeOnIoThread()
                .subscribe(observer)
    }

    protected inner class SimpleSubscriber<T> : Subscriber<T>() {

        override fun onCompleted() {
        }

        override fun onError(e: Throwable) {
        }

        override fun onNext(t: T) {
        }
    }
}
