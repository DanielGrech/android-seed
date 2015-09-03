package com.{{company_name}}.android.{{app_package_name_prefix}}.mvp.view

import android.content.Context

/**
 * Base interface for representing a view in the Model-View-Presenter architecture
 */
public interface MvpView {

    /**
     * @return The Android [Context] of the view
     */
    public fun getContext(): Context
}
