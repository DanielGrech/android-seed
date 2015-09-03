package com.{{company_name}}.android.{{app_package_name_prefix}}.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.trello.rxlifecycle.ActivityEvent
import rx.Observable
import rx.subjects.BehaviorSubject

/**
 * Activity which exposes useful lifecycle callbacks as an [Observable]
 */
abstract class RxActivity : AppCompatActivity() {

    private val lifecycleSubject = BehaviorSubject.create<ActivityEvent>()

    public fun lifecycle(): Observable<ActivityEvent> {
        return lifecycleSubject.asObservable()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleSubject.onNext(ActivityEvent.CREATE)
    }

    override fun onStart() {
        super.onStart()
        lifecycleSubject.onNext(ActivityEvent.START)
    }

    override fun onResume() {
        super.onResume()
        lifecycleSubject.onNext(ActivityEvent.RESUME)
    }

    override fun onPause() {
        lifecycleSubject.onNext(ActivityEvent.PAUSE)
        super.onPause()
    }

    override fun onStop() {
        lifecycleSubject.onNext(ActivityEvent.STOP)
        super.onStop()
    }

    override fun onDestroy() {
        lifecycleSubject.onNext(ActivityEvent.DESTROY)
        super.onDestroy()
    }
}