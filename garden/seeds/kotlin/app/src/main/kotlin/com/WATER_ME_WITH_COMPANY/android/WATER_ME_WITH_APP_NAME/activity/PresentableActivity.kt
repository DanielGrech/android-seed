package com.{{company_name}}.android.{{app_package_name_prefix}}.activity

import android.os.Bundle
import com.{{company_name}}.android.{{app_package_name_prefix}}.module.AppServicesComponent
import com.{{company_name}}.android.{{app_package_name_prefix}}.mvp.presenter.Presenter
import com.{{company_name}}.android.{{app_package_name_prefix}}.mvp.view.MvpView

/**
 * Base class for all activities which have a corresponding [Presenter] object

 * @param T The type of presenter the activity uses
 */
abstract class PresentableActivity<V : MvpView, T : Presenter<V>> : BaseActivity() {

    lateinit var presenter : T

    /**
     * Return a presenter to use for this activity. This will only be called once per activity,
     * so there is no need to cache the results

     * @param component Used for injecting dependencies
     * *
     * @return A [Presenter] instance to use with this activity
     */
    protected abstract fun createPresenter(component: AppServicesComponent): T

    override protected fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.presenter = createPresenter(getApp().getAppServicesComponent())
        this.presenter.onCreate(savedInstanceState)
    }

    override protected fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        presenter.onSaveInstanceState(outState)
    }

    override protected fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override protected fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override protected fun onPause() {
        presenter.onPause()
        super.onPause()
    }

    override protected fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override protected fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}