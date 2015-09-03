package com.{{company_name}}.android.{{app_package_name_prefix}}.activity

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import com.{{company_name}}.android.{{app_package_name_prefix}}.module.AppServicesComponent
import com.{{company_name}}.android.{{app_package_name_prefix}}.mvp.presenter.MainPresenter
import com.{{company_name}}.android.{{app_package_name_prefix}}.mvp.view.MainMvpView
import org.jetbrains.anko.*

public class MainActivity : PresentableActivity<MainMvpView, MainPresenter>(), MainMvpView {

    override fun getLayoutResource(): Int {
        return 0
    }

    override fun createPresenter(component: AppServicesComponent): MainPresenter {
        return MainPresenter(this, component)
    }

    override fun getContext(): Context {
        return this
    }

    override protected fun onCreate(savedInstanceState: Bundle?) {
        super<PresentableActivity>.onCreate(savedInstanceState)

        verticalLayout {
            textView {
                text = "Hello, Kotlin!"
                gravity = Gravity.CENTER
            }.layoutParams(width = matchParent, height = matchParent)
        }
    }

}
