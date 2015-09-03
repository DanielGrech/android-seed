package com.{{company_name}}.android.{{app_package_name_prefix}}.mvp.presenter

import com.{{company_name}}.android.{{app_package_name_prefix}}.module.AppServicesComponent
import com.{{company_name}}.android.{{app_package_name_prefix}}.mvp.view.MainMvpView

public class MainPresenter(view : MainMvpView, component : AppServicesComponent) : Presenter<MainMvpView>(view, component) {

    init {
        component.inject(this)
    }


}
