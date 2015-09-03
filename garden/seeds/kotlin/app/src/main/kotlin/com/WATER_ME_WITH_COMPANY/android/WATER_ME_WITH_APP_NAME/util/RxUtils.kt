package com.{{company_name}}.android.{{app_package_name_prefix}}.util

import com.{{company_name}}.android.{{app_package_name_prefix}}.activity.BaseActivity
import com.{{company_name}}.android.{{app_package_name_prefix}}.fragment.BaseFragment
import com.trello.rxlifecycle.RxLifecycle
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Func1
import rx.schedulers.Schedulers

fun <T> Observable<T>.bind(activity : BaseActivity) : Observable<T> {
    return this.compose(RxLifecycle.bindActivity(activity.lifecycle()));
}

fun <T> Observable<T>.bind(fragment : BaseFragment) : Observable<T> {
    return this.compose(RxLifecycle.bindFragment(fragment.lifecycle()));
}

fun <T> Observable<T>.observeOnMainThread() : Observable<T> {
    return this.observeOn(AndroidSchedulers.mainThread())
}

fun <T> Observable<T>.subscribeOnIoThread() : Observable<T> {
    return this.observeOn(Schedulers.io())
}

fun <T> Observable<T>.filterNulls() : Observable<T> {
    return this.filter { it != null }
}

fun <T, S : List<T>> Observable<S>.flatMapList() : Observable<T> {
    return flatMap { Observable.from(it) }
}