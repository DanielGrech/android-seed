package com.{{company_name}}.android.{{app_package_name_prefix}}.module

import com.{{company_name}}.android.{{app_package_name_prefix}}.mvp.presenter.MainPresenter
import dagger.Component
import javax.inject.Singleton

/**
 * Dagger component to provide dependency injection
 */
Singleton
Component(modules = arrayOf({{app_class_prefix}}Module::class))
public interface AppServicesComponent {

    fun inject(presenter: MainPresenter)

}